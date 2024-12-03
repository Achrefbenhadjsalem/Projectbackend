package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Qestion;
import com.test.COCONSULT.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    @Query("SELECT q.questionList FROM Quiz q WHERE q.id_quiz = ?1")
    List<Qestion> findAllByQuizId(int quizId);


}
