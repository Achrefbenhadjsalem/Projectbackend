package com.test.COCONSULT.ServiceIMP;


import com.test.COCONSULT.Entity.Assignements;
import com.test.COCONSULT.Entity.Projets;
import com.test.COCONSULT.Interfaces.ProjetsService;
import com.test.COCONSULT.Reposotories.ProjetsRepository;
import com.test.COCONSULT.Services.MailProject;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class ProjetsServiceImpl implements ProjetsService {

    ProjetsRepository projetsRepository;
    MailProject mailProject;


    @Override
    public List<Projets> retrieveProjets() {
        return projetsRepository.findAll();
    }

    @Override
    public Projets updateProjets(Projets projets) {
        return projetsRepository.save(projets);
    }


    @Override
    public Projets retrieveProjets(Long idProjets) {
        return projetsRepository.findById(idProjets).orElse(null);
    }

    @Override
    public void removeProjets(Long idProjets) {
        projetsRepository.deleteById(idProjets);
    }

    @Override
    public List<Assignements> getAssignementsForProject(Long projectId) {
        // Assuming you have a JPA repository for Assignements
        // and a Many-to-One relationship between Assignements and Projets
        Projets projet = projetsRepository.findById(projectId).orElse(null);
        if (projet == null) {
            // Handle case where projet with given projectId does not exist
            return Collections.emptyList();
        }
        // Create a list and add the single assignements object to it
        List<Assignements> assignementsList = new ArrayList<>();
        assignementsList.add(projet.getAssignements());
        return assignementsList;
    }
    @Override
    public Projets addProjets(Projets projets) {
        Projets newProjets = projetsRepository.save(projets);

        String subject = "Nouveau projet ajouté";
        String body = "Le projet " + projets.getProjetTitle() + " a été ajouté avec succès.";
        try {
            mailProject.send("destinataire@example.com", subject, body);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }

        return newProjets;
    }
    @Override
    public ResponseEntity<Void> validateProjet(Long id, boolean isValid) {
        Projets projets = projetsRepository.findById(id).orElse(null);
        if (projets != null) {
            projets.setValid(isValid); // Mettez à jour le statut de validation de la citation
            projetsRepository.save(projets); // Enregistrez la citation mise à jour dans la base de données
            return ResponseEntity.ok().build();
        } else {
            // Si la citation n'est pas trouvée, retournez une erreur 404
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    public Map<String, Integer> getProjectCountsByTitle() {
        List<Projets> projects = projetsRepository.findAll();
        Map<String, Integer> projectCountsByTitle = new HashMap<>();
        for (Projets project : projects) {
            String title = project.getProjetTitle();
            projectCountsByTitle.put(title, projectCountsByTitle.getOrDefault(title, 0) + 1);
        }
        return projectCountsByTitle;
    }
    @Override
    public List<Integer> getProjectDurations() {
        // Implement the logic to fetch project durations from the database
        List<Projets> projects = projetsRepository.findAll(); // Assuming you have a Project entity and ProjectRepository
        List<Integer> durations = new ArrayList<>();
        for (Projets project : projects) {
            int duration = calculateDuration(project.getDateDebut(), project.getDateFin());
            durations.add(duration);
        }
        return durations;
    }

    private int calculateDuration(LocalDate startDate, LocalDate endDate) {
        // Calcul de la période entre la date de début et la date de fin
        Period period = Period.between(startDate, endDate);

        // Récupération du nombre de jours de la période
        int days = period.getDays();

        // Récupération du nombre de mois de la période et conversion en jours
        int months = period.getMonths();
        int monthsToDays = months * 30; // En supposant que chaque mois a 30 jours

        // Récupération du nombre d'années de la période et conversion en jours
        int years = period.getYears();
        int yearsToDays = years * 365; // En supposant que chaque année a 365 jours

        // Calcul de la durée totale en jours
        int totalDays = days + monthsToDays + yearsToDays;

        return totalDays;
    }
    @Override
    public List<Projets> getProjetsByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin) {
        return projetsRepository.findByDateDebutBetween(dateDebut, dateFin);
    }
}
