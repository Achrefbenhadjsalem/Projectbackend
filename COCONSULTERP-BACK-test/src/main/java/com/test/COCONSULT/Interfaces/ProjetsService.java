package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Assignements;
import com.test.COCONSULT.Entity.Projets;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ProjetsService {
    List<Projets> retrieveProjets();

    Projets updateProjets(Projets projets);

    Projets addProjets(Projets projets);

    Projets retrieveProjets(Long idProjets);

    void removeProjets(Long idProjets);

    List<Assignements> getAssignementsForProject(Long projectId);
        static byte[] generatePdf(Long houseId) throws IOException {
            return new byte[0];
        }
    ResponseEntity<Void> validateProjet(Long id, boolean isValid);

    Map<String, Integer> getProjectCountsByTitle();
    List<Integer> getProjectDurations();

    List<Projets> getProjetsByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin);
}