package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Assignements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AssignementsRepository extends JpaRepository<Assignements, Long> {
    List<Assignements> findByProjetsIdProjet(Long idProjet);

    List<Assignements> findByProjetsIdProjetAndTimeRecordingAfter(Long idProjet, LocalDate date);

    List<Assignements> findTopNByProjetsIdProjetOrderByTimeRecordingDesc(Long idProjet);


}