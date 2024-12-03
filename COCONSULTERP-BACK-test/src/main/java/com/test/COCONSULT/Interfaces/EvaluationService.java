package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Evaluation;

public interface EvaluationService {
    public void addEvaluation(Evaluation evaluation);


    public void deleteEvaluation(int idEvaluation);
    public Evaluation getEvaluationById(int idEvaluation);


}
