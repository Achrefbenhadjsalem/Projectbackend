package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.TypeConge;
import com.test.COCONSULT.Entity.Conge;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.CongeInterface;
import com.test.COCONSULT.Reposotories.CongeRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CongeServiceImpl implements CongeInterface {

    @Autowired
    private CongeRepository congeRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public void addConge(Conge conge) {
        // Calculate the end date by adding duration to the start date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(conge.getDateDebut());
        calendar.add(Calendar.DAY_OF_MONTH, conge.getDuree()-1);
        Date endDate = calendar.getTime();

        // Set the calculated end date
        conge.setDateFin(endDate);
        congeRepository.save(conge);

        // Retrieve the User entity
        User user = userRepository.findById(conge.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Conge> setConges = user.getConges();

        if (setConges == null) {
            setConges = new HashSet<>();
        }

        setConges.add(conge);

        user.setConges(setConges);
        if (conge.getTypeConge().equals(TypeConge.Annuel)){
            user.setSoldeConge(user.getSoldeConge() - conge.getDuree());
        }

        userRepository.save(user);

    }

    @Override
    public Conge getCongeById(int idConge) {
        return null;
    }

    @Override
    public List<Conge> getCongeByUser(Long idUser) {
        return null;
    }

    @Override
    public List<Conge> getCurrentConge() {
         List<Conge> conges = congeRepository.findAll();
        List<Conge> currentConges = new ArrayList<>();
        Date currentDate = new Date();

        for (Conge conge : conges) {
            if ( conge.getDateFin().after(currentDate)) {
                currentConges.add(conge);
            }
        }
        return currentConges;
    }
}
