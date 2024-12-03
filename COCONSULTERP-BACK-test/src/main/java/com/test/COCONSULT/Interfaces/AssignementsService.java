package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Assignements;

import java.time.LocalDate;
import java.util.List;

public interface AssignementsService {

    List<Assignements> retrieveAssignements();
    Assignements updateAssignements(Assignements assignements);
    Assignements addAssignements(Assignements assignements);
    Assignements retrieveAssignements(Long idAssignements);
    void removeAssignements(Long idAssignements);
    List<Assignements> getAssignmentsForProject(Long idProjet);

   List<Assignements> getAssignmentsUpdatedAfterDate(Long idProjet, LocalDate date);

    List<Assignements> getLastAssignments(Long idProjet);
}