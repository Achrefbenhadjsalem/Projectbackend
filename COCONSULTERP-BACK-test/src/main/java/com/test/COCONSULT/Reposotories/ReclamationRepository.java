package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.DTO.ReclamationDTO;
import com.test.COCONSULT.Entity.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {
    @Query("SELECT new com.test.COCONSULT.DTO.ReclamationDTO(r.id_rec, r.content, c.prenom) FROM Reclamation r JOIN r.candidat c")
    List<ReclamationDTO> findAllReclamationsWithCandidatNames();
}


