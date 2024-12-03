package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntretienRepository extends JpaRepository<Entretien, Integer> {
}
