package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Qestion;
import com.test.COCONSULT.Entity.Quiz;
import com.test.COCONSULT.Interfaces.QuizServiceInterface;
import com.test.COCONSULT.Reposotories.QuizRepository;
import com.test.COCONSULT.ServiceIMP.QuizServiceImp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private QuizServiceInterface quizServiceInterface;
    @Autowired
    private QuizServiceImp quizServiceImp;
    @Autowired
    QuizRepository quizRepository;


    @PostMapping("/createquiz")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz createdQuiz = quizServiceInterface.createQuiz(quiz);
        return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
    }

    @PutMapping("/updatequiz/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable("id") int id, @RequestBody Quiz quiz) {
        Quiz updatedQuiz = quizServiceInterface.updateQuiz(id, quiz);
        if (updatedQuiz != null) {
            return new ResponseEntity<>(updatedQuiz, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletequiz/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable("id") int id) {
        quizServiceInterface.deleteQuiz(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getallquizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizServiceInterface.getAllQuizzes();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/getquizbyid/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable("id") int id) {
        Quiz quiz = quizServiceInterface.getQuizById(id);
        if (quiz != null) {
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/random")
    public ResponseEntity<Quiz> getRandomQuiz() {
        Quiz randomQuiz = quizServiceImp.getRandomQuiz();
        if (randomQuiz != null) {
            return new ResponseEntity<>(randomQuiz, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/questions/{id}/{mailcandidat}")
    public ResponseEntity<List<Qestion>> getQuestionsForQuiz(@PathVariable ("id") int id,@PathVariable ("mailcandidat") String mailcandidat) {

        List<Qestion> questions = quizServiceInterface.getQuestionsForQuiz(id,mailcandidat);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    @GetMapping("/questionsbyid/{id}")
    public ResponseEntity<List<Qestion>> getQuestionsForQuizByid(@PathVariable ("id") int id) {
        List<Qestion> questions = quizServiceInterface.getQuestionsForQuizbyid(id);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
@PostMapping("/verifyexistedmail/{mail}")
    public Boolean verifyexestedmail(@PathVariable ("mail") String mail) {
        return quizServiceInterface.verifyexestedmail(mail);
    }






}
