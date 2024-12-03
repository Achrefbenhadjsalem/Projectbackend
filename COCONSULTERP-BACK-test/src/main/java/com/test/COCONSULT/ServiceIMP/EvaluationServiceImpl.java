package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.TypePrime;
import com.test.COCONSULT.Entity.Evaluation;
import com.test.COCONSULT.Entity.Prime;
import com.test.COCONSULT.Entity.Salaire;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.EvaluationService;
import com.test.COCONSULT.Reposotories.EvaluationRepository;
import com.test.COCONSULT.Reposotories.PrimeRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EvaluationServiceImpl implements EvaluationService {


    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PrimeRepository primeRepository;
    @Override
    public void addEvaluation(Evaluation evaluation) {
        evaluation.setHasPrime(false);
        evaluationRepository.save(evaluation);

        User user = userRepository.findById(evaluation.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Evaluation> setEvaluations = user.getEvaluations();

        if (setEvaluations == null) {
            setEvaluations = new HashSet<>();
        }

        setEvaluations.add(evaluation);

        user.setEvaluations(setEvaluations);

        userRepository.save(user);
    }

    // Method to check evaluation and add or delete prime accordingly
    public void updatePrimesBasedOnEvaluation() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            List<Evaluation> evaluations = evaluationRepository.findByUser(user);
            for (Evaluation evaluation : evaluations) {
                if (!evaluation.isHasPrime() && evaluation.getMoy() > 7) {
                    Prime prime = new Prime();
                    prime.setMontant(50); // Montant pour les performances exceptionnelles
                    prime.setUser(user);
                    prime.setType(TypePrime.Rendement);
                    prime.setEvaluation(evaluation);
                    primeRepository.save(prime);

                    evaluation.setHasPrime(true);
                    evaluationRepository.save(evaluation);
                }
            }
        }
    }


    // Method to check sum of primes for a user and add Solde entity accordingly
    public void updateSoldeForUsers() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            double sumOfPrimes = 0.0;
            List<Prime> primes = primeRepository.findByUser(user);

            // Calculate the sum of primes for the user
            for (Prime prime : primes) {
                sumOfPrimes += prime.getMontant();
            }

            // Check if the sum of primes is greater than 100
            if (sumOfPrimes > 100.0) {
                // Add a new Solde entity for the user
                Salaire salaire = new Salaire();
                salaire.setSalaire(sumOfPrimes);
                salaire.setUser(user);
                salaire.setImpot(0);
                salaire.setDate(Date.from(new Date().toInstant()));
                salaire.setCurrency("TND");
                user.getSalaires().add(salaire);
                for (Prime prime : primes) {
                    primeRepository.delete(prime);
                }
                userRepository.save(user);
            }
        }
    }

    @Override
    public void deleteEvaluation(int idEvaluation) {
        evaluationRepository.deleteById(idEvaluation);
    }

    @Override
    public Evaluation getEvaluationById(int idEvaluation) {
        return evaluationRepository.findById(idEvaluation).get();
    }
}
