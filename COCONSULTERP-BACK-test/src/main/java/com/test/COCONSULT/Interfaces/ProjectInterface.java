package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Projets;

import java.util.List;

public interface ProjectInterface {

    Projets addProjet(Projets projet);

    public List<Projets> getAllProjets();


    Projets getProjetbyTitle(String projectTitle);

}
