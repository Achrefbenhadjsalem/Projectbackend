package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Assignements;
import com.test.COCONSULT.Entity.Expanses;
import com.test.COCONSULT.Entity.Projets;
import com.test.COCONSULT.Interfaces.AssignementsService;
import com.test.COCONSULT.Interfaces.ExpansesService;
import com.test.COCONSULT.Interfaces.ProjetsService;
import com.test.COCONSULT.ServiceIMP.DateFinValidationService;
import com.test.COCONSULT.Services.MailProject;
import com.test.COCONSULT.Services.SMSService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projets")
@AllArgsConstructor
@CrossOrigin
public class ProjetsController {


    private  SMSService smsService;
    private  MailProject mailProject;
    private  ProjetsService projetsService;
    private  ExpansesService expansesService;
    private  AssignementsService assignementsService;
    private DateFinValidationService dateFinValidationService;

    @PutMapping("/validateProject/{id}")
    public ResponseEntity<Void> validateProject(@PathVariable("id") Long id, @RequestParam("isValid") boolean isValid) {
        ResponseEntity<Void> responseEntity = projetsService.validateProjet(id, isValid);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        if (httpStatus.is2xxSuccessful()) {
            // Si la validation a réussi, récupérer le projet et envoyer un e-mail (si nécessaire)
            Projets projet = projetsService.retrieveProjets(id);
            if (projet != null) {
                String consultantEmail = projet.getMail();
                String projectName = projet.getProjetTitle();
                LocalDate projectStartDate = projet.getDateDebut();

                // Préparer le contenu de l'e-mail
                String subject = "Début de travail sur le projet";
                String body = "<html><body>" +
                        "<p>Cher Consultant,</p>" +
                        "<p>Vous êtes désigné comme le consultant responsable du projet '" + projectName + "'.</p>" +
                        "<p>Le travail sur ce projet commencera le " + projectStartDate + "</p>" +
                        "<p>Cordialement,<br>L'équipe COCONSULT</p>" +
                        "</body></html>";

                try {
                    // Envoyer l'e-mail au consultant
                    mailProject.send(consultantEmail, subject, body);
                    // Retourner une réponse 200 OK si tout s'est bien passé
                    return ResponseEntity.ok().build();
                } catch (Exception e) {
                    // Retourner une réponse 500 Internal Server Error en cas d'erreur lors de l'envoi de l'e-mail
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } else {
                // Si le projet n'a pas été trouvé, retourner une réponse 404 Not Found
                return ResponseEntity.notFound().build();
            }
        } else {
            // Si la validation a échoué, retourner la réponse de l'API externe
            return responseEntity;
        }
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<List<Projets>> retrieveProjets() {
        List<Projets> projetsList = projetsService.retrieveProjets();
        return ResponseEntity.ok(projetsList);
    }

    @GetMapping("/getProjectById/{idProjets}")
    public ResponseEntity<Projets> retrieveProjets(@PathVariable("idProjets") Long idProjets) {
        Projets projets = projetsService.retrieveProjets(idProjets);
        if (projets != null) {
            return ResponseEntity.ok(projets);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/addproject")
    public ResponseEntity<Projets> addProjets(@RequestBody Projets projets) {
        if (!dateFinValidationService.isDateFinSuperieure(projets.getDateDebut(), projets.getDateFin())) {
            throw new IllegalArgumentException("La date de fin doit être ultérieure à la date de début.");
        }
        Projets savedProjets = projetsService.addProjets(projets);
        String message = "Le projet " + projets.getProjetTitle() + " a été ajouté avec succès.";
        smsService.sendSms("+12512902845", message);
        return new ResponseEntity<>(savedProjets, HttpStatus.CREATED);
    }

    @PutMapping("/updateProject/{idProjets}")
    public ResponseEntity<Projets> updateProjets(@PathVariable("idProjets") Long idProjets, @RequestBody Projets projets) {
        if (!dateFinValidationService.isDateFinSuperieure(projets.getDateDebut(), projets.getDateFin())) {
            throw new IllegalArgumentException("La date de fin doit être ultérieure à la date de début.");
        }
        projets.setIdProjet(idProjets); // Set the ID from the path variable
        Projets updatedProjets = projetsService.updateProjets(projets);
        if (updatedProjets != null) {
            return ResponseEntity.ok(updatedProjets);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteProject/{idProjets}")
    public ResponseEntity<Void> removeProjets(@PathVariable("idProjets") Long idProjets) {
        projetsService.removeProjets(idProjets);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/expanses")
    public ResponseEntity<List<Expanses>> getExpansesForProject(@PathVariable("id") Long projectId) {
        List<Expanses> expanses = expansesService.getExpansesForProject(projectId);
        return ResponseEntity.ok(expanses);
    }

    @GetMapping("/{id}/assignements")
    public ResponseEntity<List<Assignements>> getAssignementsForProject(@PathVariable("id") Long projectId) {
        List<Assignements> assignements = assignementsService.getAssignmentsForProject(projectId);
        return ResponseEntity.ok(assignements);
    }

    @GetMapping("/counts-by-title")
    public ResponseEntity<?> getProjectCountsByTitle() {
        Map<String, Integer> projectCountsByTitle = projetsService.getProjectCountsByTitle();
        return ResponseEntity.ok(projectCountsByTitle);
    }
    @GetMapping("/durations")
    public ResponseEntity<List<Integer>> getProjectDurations() {
        List<Integer> durations = projetsService.getProjectDurations();
        return ResponseEntity.ok(durations);
    }
    @GetMapping("/date")
    public ResponseEntity<List<Projets>> getProjetsByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Projets> projets = projetsService.getProjetsByDateDebutAndDateFin(startDate, endDate);
        return new ResponseEntity<>(projets, HttpStatus.OK);
    }
}
