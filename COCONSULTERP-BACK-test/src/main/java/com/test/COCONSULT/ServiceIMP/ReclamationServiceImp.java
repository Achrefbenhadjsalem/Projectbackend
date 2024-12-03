package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.ReclamationDTO;
import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.Reclamation;
import com.test.COCONSULT.Reposotories.CandidatRepository;
import com.test.COCONSULT.Reposotories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReclamationServiceImp {
    @Autowired
    ReclamationRepository reclamationRepository;
    @Autowired
    CandidatServiceImp candidatServiceImp;
    @Autowired
    CandidatRepository candidatRepository;


    @Autowired
    public ReclamationServiceImp(ReclamationRepository reclamationRepository, CandidatServiceImp candidatServiceImp) {
        this.reclamationRepository = reclamationRepository;
        this.candidatServiceImp = candidatServiceImp;
    }

    @Transactional
    public Reclamation ajouterReclamation(String contenu, String emailCandidat) {
        // Vérifier si le candidat existe déjà dans la base de données
        Candidat candidat = candidatRepository.findByEmail(emailCandidat);

        // Si le candidat n'existe pas, créer un nouveau candidat
        if (candidat == null) {
            candidat = new Candidat();
            candidat.setEmail(emailCandidat);
            candidat = candidatRepository.save(candidat);
        }

        // Créer la réclamation
        Reclamation reclamation = new Reclamation();
        reclamation.setContent(contenu);
        // Associer la réclamation au candidat
        reclamation.setCandidat(candidat);
        reclamation = reclamationRepository.save(reclamation);

        return reclamation;
    }



    @Transactional
    public List<ReclamationDTO> getAllReclamationsWithCandidatNames() {
        return reclamationRepository.findAllReclamationsWithCandidatNames();
    }

    public void deleteReclamationById(int id) {
        reclamationRepository.deleteById(id);
    }
}

