package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Qestion;
import com.test.COCONSULT.Entity.Quiz;
import com.test.COCONSULT.Interfaces.QuestionServiceInterface;
import com.test.COCONSULT.Reposotories.QuestionRepository;
import com.test.COCONSULT.Reposotories.QuizRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class QuestionServiceImp implements QuestionServiceInterface {
    @Autowired
    private QuestionRepository questionRepository;
    private QuizRepository quizRepository;

    @Override
    public Qestion createQuestion(Qestion question) {
        return questionRepository.save(question);
    }

    @Override
    public Qestion updateQuestion(int id, Qestion question) {
        if (questionRepository.existsById(id)) {
            question.setIdQuest(id);
            return questionRepository.save(question);
        }
        return null; // You may handle this differently based on your application logic
    }

    @Override
    public void deleteQuestion(int id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Qestion> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Qestion getQuestionById(int id) {
        return questionRepository.findById(id).orElse(null);
    }




    @Transactional
    public Qestion ajouterQuestionEtReponseEtAffecterQuestionQuiz (Qestion question, Integer idQuiz) {
        Quiz quiz = quizRepository.findById(idQuiz).orElse(null);
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }
}
