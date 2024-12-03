package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.DTO.CandidatDetailsDTO;
import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.JobOpport;
import com.test.COCONSULT.Interfaces.CandidatServiceInterface;
import com.test.COCONSULT.Interfaces.FileStorageService;
import com.test.COCONSULT.Reposotories.CandidatRepository;
import com.test.COCONSULT.Reposotories.JobOpportRepository;
import com.test.COCONSULT.ServiceIMP.CandidatServiceImp;
import com.test.COCONSULT.ServiceIMP.CandidateNotificationService;
import com.test.COCONSULT.Services.MailSenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.test.COCONSULT.Entity.test;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/candidat")
public class CandidatController {
    CandidatServiceInterface candidatServiceInterface;
    private final CandidateNotificationService candidateNotificationService;
    @Autowired
    CandidatServiceImp candidatServiceImp;
    MailSenderService mailSenderService;
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CandidatRepository candidatRepository;
    private JobOpportRepository jobOpportRepository;





    @PostMapping(value = "/candidats/{candidatId}/upload-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPhoto(@PathVariable int candidatId, @RequestPart("photo") MultipartFile file) {
        // Vérifier si le candidat existe
        Optional<Candidat> optionalCandidat = candidatRepository.findById(candidatId);
        if (optionalCandidat.isPresent()) {
            Candidat candidat = optionalCandidat.get();
            String fileName = fileStorageService.storeFile(file);
            if (!fileName.isEmpty()) {
                // Mettre à jour le champ photo du candidat
                candidat.setPhoto(fileName);
                candidatRepository.save(candidat);
                return ResponseEntity.status(HttpStatus.OK).body("Photo uploaded successfully for candidat: " + candidatId);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload photo");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/createcompetence/{candidatId}")
    public ResponseEntity<?> createCompetence(@PathVariable int candidatId, @RequestParam String competence) {
        Optional<Candidat> optionalCandidat = candidatRepository.findById(candidatId);
        if (optionalCandidat.isPresent()) {
            Candidat candidat = optionalCandidat.get();
            candidat.setCompetence(competence);

            candidatRepository.save(candidat);

            return ResponseEntity.ok().body("success: " + candidatId);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }
    }
    @PutMapping("/updatecompetence/{candidatId}")
    public ResponseEntity<?> updateCompetence(@PathVariable int candidatId, @RequestParam String competence) {
        Optional<Candidat> optionalCandidat = candidatRepository.findById(candidatId);
        if (optionalCandidat.isPresent()) {
            Candidat candidat = optionalCandidat.get();
            candidat.setCompetence(competence);

            candidatRepository.save(candidat);

            return ResponseEntity.ok().body("success: " + candidatId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidat not found");
        }
    }

    @GetMapping("competence/{id}")
    public ResponseEntity<String> getCandidatcompetence(@PathVariable int id) {
        Optional<Candidat> candidatOptional = candidatRepository.findById(id);
        if (candidatOptional.isPresent()) {
            Candidat candidat = candidatOptional.get();
            return ResponseEntity.ok(candidat.getCompetence());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createcandidat/{candidatId}")
    public ResponseEntity<?> createCandidat(@PathVariable int candidatId, @RequestParam String info) {
        Optional<Candidat> optionalCandidat = candidatRepository.findById(candidatId);
        if (optionalCandidat.isPresent()) {
            Candidat candidat = optionalCandidat.get();
            candidat.setInfo(info);

            candidatRepository.save(candidat);

            return ResponseEntity.ok().body("success: " + candidatId);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getCandidatInfo(@PathVariable int id) {
        Optional<Candidat> candidatOptional = candidatRepository.findById(id);
        if (candidatOptional.isPresent()) {
            Candidat candidat = candidatOptional.get();
            return ResponseEntity.ok(candidat.getInfo());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updatecandidat/{id}")
    public ResponseEntity<Candidat> updateCandidat(@PathVariable("id") int id, @RequestBody Candidat candidat) {
        Candidat updatedCandidat = candidatServiceInterface.updateCandidat(id, candidat);
        if (updatedCandidat != null) {
            return new ResponseEntity<>(updatedCandidat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletecandidat/{id}")
    public ResponseEntity<Void> deleteCandidat(@PathVariable("id") int id) {
        candidatServiceInterface.deleteCandidat(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getallcandidat")
    public ResponseEntity<List<Candidat>> getAllCandidats() {
        List<Candidat> candidats = candidatServiceInterface.getAllCandidats();
        return new ResponseEntity<>(candidats, HttpStatus.OK);
    }

    @GetMapping("/getcandidatbyid/{id}")
    public ResponseEntity<Candidat> getCandidatById(@PathVariable("id") int id) {
        Candidat candidat = candidatServiceInterface.getCandidatById(id);
        if (candidat != null) {
            return new ResponseEntity<>(candidat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/ajouter")
    public ResponseEntity<String> ajouterTest(@RequestParam int idQuestion, @RequestParam int idQuiz, @RequestParam int idCandidat, @RequestParam String selectedAnswer) {
        candidatServiceInterface.ajouterTest(idQuestion, idQuiz, idCandidat, selectedAnswer);
        return ResponseEntity.ok("Test ajouté avec succès.");
    }

    @PostMapping("/ ajouterCandidatAOffre/{idJobOpport}")
    public void ajouterCandidatAOffre(@RequestBody Candidat candidat, @PathVariable("idJobOpport") int idJobOpport) {
        candidatServiceInterface.ajouterCandidatAOffre(candidat, idJobOpport);
    }

    @PostMapping("/add")
    public ResponseEntity<Candidat> addCandidat(@RequestParam String email) {
        // Appeler le service pour créer un candidat avec seulement l'e-mail
        Candidat newCandidat = candidatServiceImp.createCandidatWithOnlyEmail(email);
        return new ResponseEntity<>(newCandidat, HttpStatus.CREATED);
    }
    @GetMapping("/email/{email}")
    public int getCandidatIdByEmail(@PathVariable String email) {
        Candidat candidat = candidatServiceImp.getCandidatByEmail(email);

            return candidat.getId_condidat();

    }


    @GetMapping("/{email}/notify")
    public String notifyCandidate(@PathVariable("email") String candidateEmail) {
        Candidat candidat = candidatServiceInterface.getCandidatByEmail(candidateEmail);
        if (candidat == null) {
            return "Candidat with email " + candidateEmail + " not found";
        }

        double finalMark = getFinalMarkFromTests(candidat);

        String subject = "Your Final Mark";
        String body = "Your final mark is: " + finalMark;

        try {
            mailSenderService.send(candidateEmail, subject, body);
            return "Notification sent to candidate with email " + candidateEmail;
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send notification to candidate with email " + candidateEmail;
        }
    }

    private double getFinalMarkFromTests(Candidat candidat) {
        double finalMark = 0;

        for (test test : candidat.getTest()) {
            finalMark += test.getFinalmark();
        }

        return finalMark;
    }

    @GetMapping("/candidat-details")
    public ResponseEntity<List<CandidatDetailsDTO>> getCandidatDetails() {
        List<Object[]> candidatDetails = candidatServiceImp.getCandidatDetails();
        List<CandidatDetailsDTO> candidatDetailsDTOList = new ArrayList<>();

        for (Object[] objects : candidatDetails) {
            Candidat candidat = (Candidat) objects[0];
            JobOpport jobOpport = (JobOpport) objects[1];
            double finalMark = (double) objects[2];

            CandidatDetailsDTO candidatDetailsDTO = new CandidatDetailsDTO();
            candidatDetailsDTO.setCandidatId(candidat.getId_condidat());
            candidatDetailsDTO.setCandidatEmail(candidat.getEmail());
            candidatDetailsDTO.setJobOpportTitle(jobOpport.getTitre());
            candidatDetailsDTO.setFinalMark(finalMark);

            candidatDetailsDTOList.add(candidatDetailsDTO);
        }

        return ResponseEntity.ok(candidatDetailsDTOList);
    }
    @DeleteMapping("/candidat-details/{candidatId}")
    public ResponseEntity<String> deleteCandidatDetails(@PathVariable int candidatId) {
        try {
            candidatServiceImp.deleteCandidatDetails(candidatId); // Implement this method in your service
            return ResponseEntity.ok("Candidat details deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting candidat details");
        }
    }

    @GetMapping("/{candidatId}/tests/{testId}")
    public ResponseEntity<Boolean> candidatHasTakenTest(@PathVariable int candidatId, @PathVariable test testId) {
        // Récupérer le candidat
        Candidat candidat = candidatServiceImp.getCandidatById(candidatId);
        if (candidat == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }

        // Vérifier si le candidat a passé le test
        boolean hasTakenTest = candidatServiceImp.candidatHasTakenTest(candidat, testId);
        return ResponseEntity.ok(hasTakenTest);
    }
    @GetMapping("/candidats/{email}/a-passe-test")
    public boolean aPasseTest(@PathVariable String email) {
        return candidatServiceImp.aPasseTestByEmail(email);
    }





}









