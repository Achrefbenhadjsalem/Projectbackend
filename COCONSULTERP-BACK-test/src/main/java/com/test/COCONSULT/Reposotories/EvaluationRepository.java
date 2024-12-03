package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Evaluation;
import com.test.COCONSULT.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
    List<Evaluation> findByUser(User user);
}
