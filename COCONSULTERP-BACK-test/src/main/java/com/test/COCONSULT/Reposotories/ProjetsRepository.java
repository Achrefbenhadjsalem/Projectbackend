package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Projets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjetsRepository extends JpaRepository<Projets, Long> {
    @Query("SELECT p.projetTitle FROM Projets p")
    List<String> findAllProjectTitles();
    List<Projets> findByDateDebutBetween(LocalDate dateDebut, LocalDate dateFin);
    Projets getProjetByProjetTitle(String projectTitle);


}