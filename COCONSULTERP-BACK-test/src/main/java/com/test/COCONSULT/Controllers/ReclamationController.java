package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.DTO.ReclamationDTO;
import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.Reclamation;
import com.test.COCONSULT.Reposotories.CandidatRepository;
import com.test.COCONSULT.ServiceIMP.ReclamationServiceImp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
public class ReclamationController {
    @Autowired
    ReclamationServiceImp reclamationServiceImp;
    @Autowired
    CandidatRepository candidatRepository;

    @Transactional

    @PostMapping("/{emailCandidat}")
    public ResponseEntity<Reclamation> ajouterReclamation(@RequestBody String contenu, @PathVariable String emailCandidat) {
        Reclamation reclamation = reclamationServiceImp.ajouterReclamation(contenu, emailCandidat);
        return new ResponseEntity<>(reclamation, HttpStatus.CREATED);
    }

    @Transactional

    @GetMapping("/all")

    public List<ReclamationDTO> getAllReclamationsWithCandidatNames() {
        return reclamationServiceImp.getAllReclamationsWithCandidatNames();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReclamation(@PathVariable("id") int id) {
        reclamationServiceImp.deleteReclamationById(id);
        return ResponseEntity.ok("Reclamation deleted successfully");
    }}

