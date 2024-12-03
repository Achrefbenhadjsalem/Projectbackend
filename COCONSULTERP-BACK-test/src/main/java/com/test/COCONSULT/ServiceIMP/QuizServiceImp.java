package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.ReclamationDTO;
import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.JobOpport;
import com.test.COCONSULT.Entity.Qestion;
import com.test.COCONSULT.Entity.Quiz;
import com.test.COCONSULT.Interfaces.QuizServiceInterface;
import com.test.COCONSULT.Reposotories.CandidatRepository;
import com.test.COCONSULT.Reposotories.JobOpportRepository;
import com.test.COCONSULT.Reposotories.QuizRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class QuizServiceImp implements QuizServiceInterface {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    CandidatRepository candidatRepository;
    @Autowired
    JobOpportRepository jobOpportRepository;

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(int id, Quiz quiz) {
        if (quizRepository.existsById(id)) {
            quiz.setId_quiz(id);
            return quizRepository.save(quiz);
        }
        return null; // You may handle this differently based on your application logic
    }


    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuizById(int id) {
        return quizRepository.findById(id).orElse(null);
    }

    @Override

    public List<Qestion> getQuestionsForQuiz(int quizId, String mail) {
        Candidat candidat = candidatRepository.findByEmail(mail);
        if (candidat != null) {
            Quiz quiz = quizRepository.findById(quizId).orElse(null);

            if (quiz != null) {
                // Return the list of questions associated with the quiz
                return quiz.getQuestionList();
            }
        }
        return null;
    }


    @Override
    public List<Qestion> getQuestionsForQuizbyid(int quizId) {

        Quiz quiz = quizRepository.findById(quizId).orElse(null);

        if (quiz != null) {
            // Return the list of questions associated with the quiz
            return quiz.getQuestionList();
        } else {
            // Quiz with the provided ID not found
            throw new IllegalArgumentException("Quiz with ID " + quizId + " not found");
        }

    }

    @Override
    public Boolean verifyexestedmail(String mail) {
        if (candidatRepository.findByEmail(mail) != null) {
            return true;
        }
        return false;
    }



    public void deleteQuiz(int id) {
        // Récupérer le quiz à supprimer
        Quiz quizToDelete = quizRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + id));

        // Supprimer le quiz (et les questions associées seront supprimées automatiquement en cascade)
        quizRepository.delete(quizToDelete);
    }
    public Quiz getRandomQuiz() {
        List<Quiz> allQuizzes = quizRepository.findAll(); // Récupérer tous les quizzes depuis la base de données

        // Générer un index aléatoire pour choisir un quiz aléatoire dans la liste
        Random random = new Random();
        int randomIndex = random.nextInt(allQuizzes.size());

        // Récupérer et retourner le quiz aléatoire
        return allQuizzes.get(randomIndex);
    }



}