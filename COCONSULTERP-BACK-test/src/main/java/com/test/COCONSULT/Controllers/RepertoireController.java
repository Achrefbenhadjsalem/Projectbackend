package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Prospect;
import com.test.COCONSULT.Entity.Repertoire;
import com.test.COCONSULT.Interfaces.RepertoireService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("Repertoire")
@RestController
@AllArgsConstructor
public class RepertoireController {
    private final RepertoireService repertoireService ;

    @GetMapping("/GetAllRepertoire")
    public ResponseEntity<List<Repertoire>> retrieveRepertoire() {
        List<Repertoire> RepertoireList = repertoireService.retrieveRepertoire();
        return ResponseEntity.ok(RepertoireList);
    }

    @GetMapping("/GetRepertoireByID/{id}")
    public ResponseEntity<Repertoire> retrieveRepertoire(@PathVariable("id") Long idContact ) {
        Repertoire repertoire =  repertoireService.retrieveRepertoire(idContact);
        if (repertoire != null) {
            return ResponseEntity.ok(repertoire);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ajouterRepertoire")
    public Repertoire addRepertoire(@RequestBody Repertoire repertoire) {
        return repertoireService.addRepertoire(repertoire);

    }

    @PutMapping("/updateRepertoire/{id}")
    public ResponseEntity<Repertoire> updateRepertoire(@PathVariable("id") Long idContact, @RequestBody Repertoire repertoire) {
        repertoire.setIdRepertoire(idContact); // Set the ID from the path variable
        Repertoire updatedrepetoire = repertoireService.updateRepertoire(repertoire);
        if (updatedrepetoire != null) {
            return ResponseEntity.ok(updatedrepetoire);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/RemoveRepertoire/{id}")
    public ResponseEntity<Void> removeRepertoire(@PathVariable("id") Long idContact) {
        repertoireService.removeRepertoire(idContact);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/createRepertoireFromProspect")
    public ResponseEntity<Repertoire> createRepertoireFromProspect(@RequestBody Prospect prospect) {
        Repertoire repertoire = repertoireService.createRepertoireFromProspect(prospect);
        return ResponseEntity.ok(repertoire);
    }
}
