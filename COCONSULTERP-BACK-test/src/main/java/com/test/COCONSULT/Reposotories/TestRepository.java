package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestRepository extends JpaRepository<test, Integer> {
    test findByCandidat(Candidat candidat);
    @Query("SELECT t.candidat.email, q.titre, t.finalmark FROM test t JOIN t.quiz q")
    List<Object[]> findCandidateEmailAndQuizTitleAndFinalMark();
}

