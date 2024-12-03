
package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.ActivitySalesTeam;
import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Interfaces.ActivitySalesTeamService;
import com.test.COCONSULT.Services.SMSServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("ActivitySalesTeam")
@RestController
@AllArgsConstructor

public class ActivitySalesTeamController {
    private final ActivitySalesTeamService activitySalesTeamService ;
    @Autowired
    SMSServices smsServices;
    @GetMapping("/GetAllActSalesTeam")
    public ResponseEntity<List<ActivitySalesTeam>> retrieveActivitySalesTeam() {
        List<ActivitySalesTeam> ActivitySalesTeamList = activitySalesTeamService.retrieveActivitySalesTeam();
        return ResponseEntity.ok(ActivitySalesTeamList);
    }

    @GetMapping("/GetActSalesTeamByID/{id}")
    public ResponseEntity<ActivitySalesTeam> retrieveActivitySalesTeam(@PathVariable("id") Long idActSale) {
        ActivitySalesTeam activitySalesTeam = activitySalesTeamService.retrieveActivitySalesTeam(idActSale);
        if (activitySalesTeam != null) {
            return ResponseEntity.ok(activitySalesTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/ajouterActSalesTeam")
    public ActivitySalesTeam addActivitySalesTeam(@RequestBody ActivitySalesTeam activitySalesTeam) {
        return activitySalesTeamService.addActivitySalesTeam(activitySalesTeam);

    }

    @PutMapping("/updateActSalesTeam/{id}")
    public ResponseEntity<ActivitySalesTeam> updateActivitySalesTeam(@PathVariable("id") Long idActSales, @RequestBody ActivitySalesTeam activitySalesTeam) {
        activitySalesTeam.setIdActSale(idActSales); // Set the ID from the path variable
        ActivitySalesTeam updatedActivitySalesTeam = activitySalesTeamService.updateActivitySalesTeam(activitySalesTeam);
        if (updatedActivitySalesTeam != null) {
            return ResponseEntity.ok(updatedActivitySalesTeam);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/RemoveActSales/{id}")
    public ResponseEntity<Void> removeActivitySalesTeam(@PathVariable("id") Long idActSales) {
        activitySalesTeamService.removeActivitySalesTeam(idActSales);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/GetActivitySalesTeamByClass/{classSalesTeam}")
    public ResponseEntity<List<ActivitySalesTeam>> getActivitySalesTeamByClass(@PathVariable String classSalesTeam) {
        List<ActivitySalesTeam> activitySalesTeamList = activitySalesTeamService.getActivitySalesTeamByClass(classSalesTeam);
        return ResponseEntity.ok(activitySalesTeamList);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<ActivitySalesTeam> updateStatus(@PathVariable("id") Long idActSales) {
        ActivitySalesTeam updatedActivitySalesTeam = activitySalesTeamService.updateStatus(idActSales);
        if (updatedActivitySalesTeam != null) {
            return ResponseEntity.ok(updatedActivitySalesTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ajouterActivitySalesTeam/{repertoireId}")
    public ResponseEntity<ActivitySalesTeam> addActivityAffectRep(@RequestBody ActivitySalesTeam activitySalesTeam, @PathVariable Long repertoireId) {
        ActivitySalesTeam addedActivitySalesTeam = activitySalesTeamService.addActivityAffectRep(activitySalesTeam, repertoireId);
        if (addedActivitySalesTeam != null) {
            return ResponseEntity.ok(addedActivitySalesTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ajouterActivitySalesTeamSendSMS/{repertoireId}")
    public ResponseEntity<ActivitySalesTeam> addActivityAffectRepSendSMS(@RequestBody ActivitySalesTeam activitySalesTeam, @PathVariable Long repertoireId) {
        ActivitySalesTeam addedActivitySalesTeam = activitySalesTeamService.addActivityAffectRep(activitySalesTeam, repertoireId);
        if (addedActivitySalesTeam != null) {
            smsServices.sendSms("+12532184720", "hihi");
            return ResponseEntity.ok(addedActivitySalesTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
