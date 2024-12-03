package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.Qestion;
import com.test.COCONSULT.Entity.Quiz;
import com.test.COCONSULT.Entity.test;
import com.test.COCONSULT.Interfaces.CandidatServiceInterface;
import com.test.COCONSULT.Interfaces.QuestionServiceInterface;
import com.test.COCONSULT.Reposotories.CandidatRepository;
import com.test.COCONSULT.Reposotories.QuestionRepository;
import com.test.COCONSULT.Reposotories.QuizRepository;
import com.test.COCONSULT.Reposotories.TestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("")
public class TestController {

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
private CandidatServiceInterface candidatServiceInterface;
    @Autowired
    private QuestionServiceInterface questionService;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    TestRepository testRepository;
@Autowired
    QuizRepository quizRepository;
    /*@PostMapping("/evaluatequiz/{idQuest}/{option}/{mailcandidat}")
    public ResponseEntity<?> evaluateQuiz( @PathVariable("idQuest") int idQuest,@PathVariable("option") String option, @PathVariable("mailcandidat") String mailcandidat)
    {
        // Find the candidate by email
        Candidat candidat = candidatRepository.findByEmail(mailcandidat);
        if (candidat == null) {
            return ResponseEntity.notFound().build();
        }

        test attemptedTest = new test();
        attemptedTest.setCandidat(candidat);

        Integer correctAnswers = 0;
        double marksObtained = 0;
        Integer attempted = 0;
            Qestion qestion=questionRepository.findById(idQuest).orElse(null);

            if(qestion!=null){
            try {
                // Compare the given answer with the correct answer
                if (qestion.getAnswer().equals(option)) {
                    correctAnswers++;
                }
                attempted++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Calculate marks obtained
        double maxMarksPerQuestion  = qestion.getPonderation();
        marksObtained = correctAnswers * maxMarksPerQuestion;

        // Assign values to attemptedTest entity
        List<Qestion> reponsesquestion=new ArrayList<>();
        reponsesquestion.add(qestion);
        attemptedTest.setQuestions(reponsesquestion); // Add attempted questions
        Quiz quiz =quizRepository.findById(qestion.getIdQuest()).orElse(null);
        attemptedTest.getQuiz().add(quiz); // Assign the quiz
        // You may need to set other properties of attemptedTest as per your requirement
        attemptedTest.setFinalmark(marksObtained);
        // Save the attempted test to the database or perform further actions
        testRepository.save(attemptedTest);
        return ResponseEntity.ok("Quiz evaluated successfully. Correct Answers: " + correctAnswers +
                ", Marks Obtained: " + marksObtained + ", Total Questions Attempted: " + attempted);
    }*/
    @PutMapping("/evaluatequiz/{idQuest}/{option}/{mailcandidat}")
    public ResponseEntity<?> evaluateQuiz(@PathVariable ("idQuest") int questionsid ,
                                          @PathVariable("option") String option,
                                          @PathVariable("mailcandidat") String mailcandidat) {

        Integer correctAnswers = 0;

        Integer attempted = 0;
        // Find the question by id

        Qestion question = questionRepository.findById(questionsid).orElse(null);

        // Find the candidate by email
        Candidat candidat = candidatRepository.findByEmail(mailcandidat);
        if (candidat == null) {
            return ResponseEntity.notFound().build();
        }
        //find an existed test for that candidate
        test existedtest=testRepository.findByCandidat(candidat);

        if (existedtest!=null){
            double marksObtainedexisted = existedtest.getFinalmark();
            if (question != null) {
                if(!existedtest.getQuestions().contains(question)){
                    // Compare the given answer with the correct answer
                    if (question.getAnswer().equals(option)) {
                        correctAnswers++;
                    }
                    attempted++;

                    double maxMarksPerQuestion = question.getPonderation();
                    marksObtainedexisted += correctAnswers * maxMarksPerQuestion;

                    existedtest.getQuestions().add(question);
                    existedtest.setFinalmark(marksObtainedexisted);
                    testRepository.save(existedtest);

                    return ResponseEntity.ok(" new question added for this test  ");

                }
                else {
                    return ResponseEntity.ok(" you have already do this test  ");

                }

            }
        }
         else{
             //create a new test for new candidat
                test attemptedTest = new test();
                attemptedTest.setCandidat(candidat);
                    double marksObtained=0;

                if (question != null) {
                    // Compare the given answer with the correct answer
                    if (question.getAnswer().equals(option)) {
                        correctAnswers++;
                    }
                    attempted++;

                    double maxMarksPerQuestion = question.getPonderation();
                    marksObtained += correctAnswers * maxMarksPerQuestion;

                    attemptedTest.getQuestions().add(question);
                    attemptedTest.setFinalmark(marksObtained);
                    Quiz quiz = question.getQuiz();
                    if (quiz != null) {
                        attemptedTest.getQuiz().add(quiz); // Assign the quiz
                    }
                    testRepository.save(attemptedTest);
                    return ResponseEntity.ok(" new test added ");

                }

            return ResponseEntity.notFound().build();

        }


        // Save the attempted test to the database or perform further actions

        return ResponseEntity.badRequest().body("nothing is done  ");



    }



}

