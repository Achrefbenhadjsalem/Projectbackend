package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Qestion;
import com.test.COCONSULT.Interfaces.QuestionServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@AllArgsConstructor
@RestController
@RequestMapping("/question")
public class QuestionController {
@Autowired
    QuestionServiceInterface questionServiceInterface;
    @PostMapping("/createquestion")
    public ResponseEntity<Qestion> createQuestion(@RequestBody Qestion question) {
        Qestion createdQuestion = questionServiceInterface.createQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/updatequestion/{id}")
    public ResponseEntity<Qestion> updateQuestion(@PathVariable("id") int id, @RequestBody Qestion question) {
        Qestion updatedQuestion = questionServiceInterface.updateQuestion(id, question);
        if (updatedQuestion != null) {
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletequestion/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") int id) {
        questionServiceInterface.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getallquestion")
    public ResponseEntity<List<Qestion>> getAllQuestions() {
        List<Qestion> questions = questionServiceInterface.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/getquestionbyid/{id}")
    public ResponseEntity<Qestion> getQuestionById(@PathVariable("id") int id) {
        Qestion question = questionServiceInterface.getQuestionById(id);
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/affecterquaqui/{id}")
    public Qestion ajouterQuestionEtReponseEtAffecterQuestionQuiz( @RequestBody Qestion question,@PathVariable("id") Integer idQuiz) {
        return questionServiceInterface.ajouterQuestionEtReponseEtAffecterQuestionQuiz(question, idQuiz);
    }
}
