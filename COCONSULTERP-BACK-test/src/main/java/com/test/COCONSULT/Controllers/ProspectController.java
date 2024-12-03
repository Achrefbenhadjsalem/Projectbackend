package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Payment;
import com.test.COCONSULT.Entity.Prospect;
import com.test.COCONSULT.Interfaces.ProspectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequestMapping("Prospect")
@RestController
@AllArgsConstructor
public class ProspectController {
    private final ProspectService prospectService ;

    @GetMapping("/GetAllProspect")
    public ResponseEntity<List<Prospect>> retrieveProspect() {
        List<Prospect> ProspectList = prospectService.retrieveProspect();
        return ResponseEntity.ok(ProspectList);
    }

    @GetMapping("/GetProspectByID/{id}")
    public ResponseEntity<Prospect> retrieveProspect(@PathVariable("id") Long idProspect ) {
        Prospect prospect =  prospectService.retrieveProspect(idProspect);
        if (prospect != null) {
            return ResponseEntity.ok(prospect);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/ajouterProspect")
    public Prospect addProspect(@RequestBody Prospect prospect) {
        return prospectService.addProspect(prospect);

    }

    @PutMapping("/updateProspect/{id}")
    public ResponseEntity<Prospect> updateProspect(@PathVariable("id") Long idProspect, @RequestBody Prospect prospect) {
        prospect.setIdProspect(idProspect); // Set the ID from the path variable
        Prospect updatedProspect = prospectService.updateProspect(prospect);
        if (updatedProspect != null) {
            return ResponseEntity.ok(updatedProspect);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/RemoveProspect/{id}")
    public ResponseEntity<Void> removeProspect(@PathVariable("id") Long idProspect) {
        prospectService.removeProspect(idProspect);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload-prospects-data")
    public ResponseEntity<?> uploadProspectsData(@RequestParam("file") MultipartFile file){
        this.prospectService.saveProspectsToDatabase(file);
        return ResponseEntity.ok(Map.of("Message" , "Prospects data uploaded and saved to database successfully"));
    }
}