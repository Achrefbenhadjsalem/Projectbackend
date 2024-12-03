package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Qestion;

import java.util.List;

public interface QuestionServiceInterface {
    Qestion createQuestion(Qestion question);
    Qestion updateQuestion(int id, Qestion question);
    void deleteQuestion(int id);
    List<Qestion> getAllQuestions();
    Qestion getQuestionById(int id);
    Qestion ajouterQuestionEtReponseEtAffecterQuestionQuiz (Qestion question, Integer idQuiz);


}
