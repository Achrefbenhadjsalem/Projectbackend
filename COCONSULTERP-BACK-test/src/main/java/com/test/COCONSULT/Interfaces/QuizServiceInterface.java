package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Qestion;
import com.test.COCONSULT.Entity.Quiz;

import java.util.List;

public interface QuizServiceInterface {
    Quiz createQuiz(Quiz quiz);
    Quiz updateQuiz(int id, Quiz quiz);
    void deleteQuiz(int id);
    List<Quiz> getAllQuizzes();
    Quiz getQuizById(int id);
    List<Qestion> getQuestionsForQuiz(int quizId,String mail);
    List<Qestion> getQuestionsForQuizbyid(int quizId );
    Boolean verifyexestedmail(String mail);



}
