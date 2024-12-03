package com.test.COCONSULT.Tasks;

import com.test.COCONSULT.ServiceIMP.EvaluationServiceImpl;
import com.test.COCONSULT.ServiceIMP.PointageServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PrimeAndEvaluationUpdater implements ApplicationRunner {

    @Autowired
    private EvaluationServiceImpl evaluationService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Call the method to update primes and evaluations once the application is launched
        evaluationService.updatePrimesBasedOnEvaluation();
        evaluationService.updateSoldeForUsers();
    }
}
