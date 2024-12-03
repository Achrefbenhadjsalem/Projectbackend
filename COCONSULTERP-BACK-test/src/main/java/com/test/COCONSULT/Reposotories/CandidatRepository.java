package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
    Candidat findByEmail(String mail);
    boolean existsByEmail(String email);
    @Query("SELECT c, c.jobOpport, t.finalmark FROM Candidat c JOIN c.test t")
    List<Object[]> findCandidatDetails();

    List<Candidat> findByCompetenceContaining(String competence);
    List<Candidat> findByCompetenceInIgnoreCase(List<String> competence);


}
