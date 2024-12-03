package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Evaluation;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Reposotories.EvaluationRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import com.test.COCONSULT.ServiceIMP.EvaluationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {

    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    EvaluationServiceImpl evaluationService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/add-evaluation/")
    public void addEvaluation(@RequestBody Evaluation evaluation) {
        evaluationService.addEvaluation(evaluation);
    }

    //get average evaluation of a user
    @GetMapping("/get-average-evaluation/{idUser}")
    public double getAverageEvaluation(@PathVariable("idUser") Long idUser) {
       User user = userRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + idUser));
        List<Evaluation> evaluations = evaluationRepository.findByUser(user);
        double sum = 0;
        for (Evaluation evaluation : evaluations) {
            sum += evaluation.getMoy();
        }
        //round it to int
        return Math.round(sum / evaluations.size());
    }


}
