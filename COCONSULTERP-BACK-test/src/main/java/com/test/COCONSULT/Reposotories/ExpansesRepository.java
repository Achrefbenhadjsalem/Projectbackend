package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Expanses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpansesRepository extends JpaRepository<Expanses, Long> {
    List<Expanses> findByProjetsIdProjet(Long projectId);
    //List<Expanses> findByQuoteId(Long quoteId);
    List<Expanses> findByProjetsIdProjetAndDateAfter(Long projectId, LocalDate date);

    List<Expanses> findTopNByProjetsIdProjetOrderByDateDesc(Long idProjet);
}