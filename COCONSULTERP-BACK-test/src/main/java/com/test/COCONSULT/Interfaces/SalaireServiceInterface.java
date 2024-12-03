package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Salaire;

import java.util.List;

public interface SalaireServiceInterface {
    public void addSalaire(Salaire salaire);
    public void deleteSalaire(int idSalaire);
    public Salaire getSalaireById(int idSalaire);

    public List<Salaire> getSalaireByUser(Long idUser);

    public Salaire updateSalaire(Salaire salaire);


}
