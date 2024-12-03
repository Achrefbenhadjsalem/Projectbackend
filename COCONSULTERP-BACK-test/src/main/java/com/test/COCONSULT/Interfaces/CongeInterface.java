package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Conge;

import java.util.List;

public interface CongeInterface {
    public void addConge(Conge conge);

    public Conge getCongeById(int idConge);

    public List<Conge> getCongeByUser(Long idUser);

    public List<Conge> getCurrentConge();

}
