package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.JobOpport;
import com.test.COCONSULT.Entity.test;

import java.util.List;

public interface CandidatServiceInterface {
    Candidat createCandidat(Candidat candidat);
    Candidat updateCandidat(int id, Candidat candidat);
    void deleteCandidat(int id);
    List<Candidat> getAllCandidats();
    Candidat getCandidatById(int id);

    void ajouterTest(int idQuestion, int idQuiz, int idCandidat, String selectedAnswer);
    void ajouterCandidatAOffre(Candidat candidat, int idJobOpport);
    Candidat createCandidatWithOnlyEmail(String email);
    Candidat getCandidatByEmail(String email);
    public boolean candidatHasTakenTest(Candidat candidat, test test);

}
