package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.ProjFeed;
import com.test.COCONSULT.Interfaces.ProjFeedService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projfeeds")
@CrossOrigin
@AllArgsConstructor
public class ProjFeedController {

    private ProjFeedService projFeedService;


    @GetMapping("getAllPrjFeed")
    public ResponseEntity<List<ProjFeed>> retrieveProjFeeds() {
        List<ProjFeed> projFeeds = projFeedService.retrieveProjFeeds();
        return ResponseEntity.ok(projFeeds);
    }

    @GetMapping("/getPrjFeedById/{idProjFeed}")
    public ResponseEntity<ProjFeed> retrieveProjFeed(@PathVariable("idProjFeed") Long idProjFeed) {
        ProjFeed projFeed = projFeedService.retrieveProjFeed(idProjFeed);
        if (projFeed != null) {
            return ResponseEntity.ok(projFeed);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ajouterPrjFeed")
    public ResponseEntity<ProjFeed> addProjFeed(@RequestBody ProjFeed projFeed) {
        ProjFeed savedProjFeed = projFeedService.addProjFeed(projFeed);
        return new ResponseEntity<>(savedProjFeed, HttpStatus.CREATED);
    }

    @PutMapping("/updatePfeed/{idProjFeed}")
    public ResponseEntity<ProjFeed> updateProjFeed(@PathVariable("idProjFeed") Long idProjFeed, @RequestBody ProjFeed projFeed) {
        projFeed.setIdPjtFeed(idProjFeed); // Set the ID from the path variable
        ProjFeed updatedProjFeed = projFeedService.updateProjFeed(projFeed);
        if (updatedProjFeed != null) {
            return ResponseEntity.ok(updatedProjFeed);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletePrjFeed/{idProjFeed}")
    public ResponseEntity<Void> removeProjFeed(@PathVariable("idProjFeed") Long idProjFeed) {
        projFeedService.removeProjFeed(idProjFeed);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/projects")
    public ResponseEntity<List<ProjFeed>> getProjFeedWithProjects(@PathVariable("id") Long id) {
        List<ProjFeed> projFeeds = projFeedService.getProjFeedWithProjects(id);
        return ResponseEntity.ok(projFeeds);
    }


    @GetMapping("/updatedAfterDate/{date}")
    public ResponseEntity<List<ProjFeed>> getProjFeedUpdatedAfterDate(@PathVariable("date") String date) {
        List<ProjFeed> projFeeds = projFeedService.getProjFeedsUpdatedAfterDate(date);
        return ResponseEntity.ok(projFeeds);
    }

    @GetMapping("/last/{limit}")
    public ResponseEntity<List<ProjFeed>> getLastProjFeeds(@PathVariable("limit") int limit) {
        List<ProjFeed> projFeeds = projFeedService.getLastProjFeeds(limit);
        return ResponseEntity.ok(projFeeds);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalProjFeedsCount() {
        Long count = projFeedService.getTotalProjFeedsCount();
        return ResponseEntity.ok(count);
    }

}
