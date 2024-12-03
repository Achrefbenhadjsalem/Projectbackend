package com.test.COCONSULT.Interfaces;


import com.test.COCONSULT.Entity.Expanses;
import java.time.LocalDate;
import java.util.List;

public interface ExpansesService {

    List<Expanses> retrieveExpanses();
    Expanses updateExpanses(Expanses expanses);
    Expanses addExpanses(Expanses expanses);
    Expanses retrieveExpanses(Long idExpanses);
    void removeExpanses(Long idExpanses);
    List<Expanses> getExpansesForProject(Long projectId);

    List<Expanses> getExpansesUpdatedAfterDate(Long projectId, LocalDate date);

    List<Expanses> getLastExpanses(Long projectId, int limit);
}