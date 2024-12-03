package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Projets;
import com.test.COCONSULT.Reposotories.ProjetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectInterface implements com.test.COCONSULT.Interfaces.ProjectInterface {
    @Autowired
    ProjetsRepository projetRepository;
    @Override
    public Projets addProjet(Projets projet) {
        return projetRepository.save(projet);
    }

    @Override
    public List<Projets> getAllProjets() {
        return projetRepository.findAll();
    }

    @Override
    public Projets getProjetbyTitle(String projectTitle) {
        return projetRepository.getProjetByProjetTitle(projectTitle);
    }
}
