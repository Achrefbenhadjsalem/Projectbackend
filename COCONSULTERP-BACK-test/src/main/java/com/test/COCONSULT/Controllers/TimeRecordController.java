package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.TimeRecord;
import com.test.COCONSULT.Interfaces.TimeRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/time-records")
@AllArgsConstructor
@CrossOrigin
public class TimeRecordController {

    private  TimeRecordService timeRecordService;

    @GetMapping("getAllTimeRec")
    public ResponseEntity<List<TimeRecord>> retrieveTimeRecords() {
        List<TimeRecord> timeRecords = timeRecordService.retrieveTimeRec();
        return ResponseEntity.ok(timeRecords);
    }

    @GetMapping("/getTimeRecById/{idTimeRec}")
    public ResponseEntity<TimeRecord> retrieveTimeRecord(@PathVariable("idTimeRec") Long idTimeRec) {
        TimeRecord timeRecord = timeRecordService.retrieveTimeRecord(idTimeRec);
        if (timeRecord != null) {
            return ResponseEntity.ok(timeRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ajouterTimeRec")
    public ResponseEntity<TimeRecord> addTimeRecord(@RequestBody TimeRecord timeRecord) {
        TimeRecord savedTimeRecord = timeRecordService.ajouterTimeRec(timeRecord);
        return new ResponseEntity<>(savedTimeRecord, HttpStatus.CREATED);
    }

    @PutMapping("/updateTimeRec/{idTimeRec}")
    public ResponseEntity<TimeRecord> updateTimeRecord(@PathVariable("idTimeRec") Long idTimeRec, @RequestBody TimeRecord timeRecord) {
        timeRecord.setIdTimeRec(idTimeRec); // Set the ID from the path variable
        TimeRecord updatedTimeRecord = timeRecordService.updateTimeRec(timeRecord);
        if (updatedTimeRecord != null) {
            return ResponseEntity.ok(updatedTimeRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteTimeRec/{idTimeRec}")
    public ResponseEntity<Void> removeTimeRecord(@PathVariable("idTimeRec") Long idTimeRec) {
        timeRecordService.removeTimeRec(idTimeRec);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getTimeRecForProject/{projectId}")
    public ResponseEntity<List<TimeRecord>> getTimeRecordsForProject(@PathVariable("projectId") Long projectId) {
        List<TimeRecord> timeRecords = timeRecordService.getTimeRecordsForProject(projectId);
        return ResponseEntity.ok(timeRecords);
    }


    @GetMapping("/getTotalTimeForProject/{projectId}")
    public ResponseEntity<Double> getTotalTimeForProject(@PathVariable("projectId") Long projectId) {
        Double totalTime = timeRecordService.getTotalTimeForProject(projectId);
        return ResponseEntity.ok(totalTime);
    }

    @GetMapping("/getTimeRecByCriteria")
    public ResponseEntity<List<TimeRecord>> retrieveTimeRecordsByCriteria(@RequestParam Map<String, String> criteria) {
        List<TimeRecord> timeRecords = timeRecordService.retrieveTimeRecByCriteria(criteria);
        return ResponseEntity.ok(timeRecords);
    }

    @PostMapping("/{timeRecordId}/assign/{projectId}")
    public void assignTimeRecordToProject(@PathVariable Long timeRecordId, @PathVariable Long projectId) {
        timeRecordService.assignTimeRecordToProject(timeRecordId, projectId);
    }
}
