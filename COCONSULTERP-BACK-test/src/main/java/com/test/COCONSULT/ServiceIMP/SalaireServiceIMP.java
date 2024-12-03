package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.Role;
import com.test.COCONSULT.Entity.Salaire;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.SalaireServiceInterface;
import com.test.COCONSULT.Reposotories.RoleRepository;
import com.test.COCONSULT.Reposotories.SalaireRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SalaireServiceIMP implements SalaireServiceInterface {

    @Autowired
    private SalaireRepository salaireRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void addSalaire(Salaire salaire) {
        // Save the Salaire entity
        salaire.setDate(Date.from(new Date().toInstant()));
        salaireRepository.save(salaire);

        // Retrieve the User entity
        User user = userRepository.findById(salaire.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Retrieve the existing Set of Salaires from the User entity
        Set<Salaire> setSalaires = user.getSalaires();

        // initialize it with a new HashSet
        if (setSalaires == null) {
            setSalaires = new HashSet<>();
        }

        // Add the new Salaire to the setSalaires
        setSalaires.add(salaire);

        // Set the modified setSalaires back to the User entity
        user.setSalaires(setSalaires);

        // Save the updated User entity
        userRepository.save(user);
    }

    @Override
    public void deleteSalaire(int idSalaire) {
        Salaire salaire = salaireRepository.findById(idSalaire).get();
        User user = userRepository.findById(salaire.getUser().getId()).get();
        user.getSalaires().remove(salaire);
        userRepository.save(user);
        salaireRepository.deleteById(idSalaire);
    }

    @Override
    public Salaire getSalaireById(int idSalaire) {
        return salaireRepository.findById(idSalaire).get();
    }

    @Override
    public List<Salaire> getSalaireByUser(Long idUser) {
        return salaireRepository.findByUser_Id(idUser);
    }

    public List<Salaire> get(){
        return salaireRepository.findAll();
    }
    @Override
    public Salaire updateSalaire(Salaire salaire) {
        return salaireRepository.save(salaire);
    }

    //add salaire to user based on role
    public void addSalaireToAllUsers() {
        RoleName[] roles = RoleName.values();

        for (RoleName roleName : roles) {
            Role role = roleRepository.findByName(roleName).orElse(null)
                  ;
            List<User> users = userRepository.findByRolesContains(role);

            for (User user : users) {
                boolean isDecember31st = LocalDate.now().getMonthValue() == 12 && LocalDate.now().getDayOfMonth() == 31;

                Salaire salaire = new Salaire();
                salaire.setImpot(30);
                salaire.setCurrency("TND");
                salaire.setDate(Date.from(new Date().toInstant()));
                salaire.setUser(user);

                double baseSalaire = calculateSalaireBasedOnRoleAndExperience(roleName, user.getExp());
                double finalSalaire = isDecember31st ? baseSalaire * 1.1 : baseSalaire;

                salaire.setSalaire(finalSalaire);
                Set<Salaire> setSalaires = user.getSalaires();
                setSalaires.add(salaire);
                user.setSalaires(setSalaires);

                // Sauvegarder l'utilisateur et le salaire
                userRepository.save(user);
                salaireRepository.save(salaire);
            }
        }
    }

    private int calculateSalaireBasedOnRoleAndExperience(RoleName roleName, String exp) {
        int baseSalaire = 1000;
        switch (roleName) {
            case Manager:
            case HR:
                baseSalaire = 3000;
                break;
            case Employee:
            case CRM:
            case Consult:
                baseSalaire = 2000;
                break;
            case PM:
                baseSalaire = 3500;
                break;
            default:
                baseSalaire = 1500;
                break;
        }

        // Ajouter un bonus selon l'expérience


        return baseSalaire;
    }
}
