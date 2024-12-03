package com.test.COCONSULT.Controllers;


import com.test.COCONSULT.Entity.JobOpport;
import com.test.COCONSULT.Interfaces.JobOpportServiceInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


    @RequestMapping("/jobopports")
    public class JobOpportController {
    @Autowired
JobOpportServiceInterface jobOpportServiceInterface;
@PostMapping("/add")
    public void addJobOpportunity( @RequestBody  JobOpport jobOpport) {
        jobOpportServiceInterface.addJobOpportunity(jobOpport);
    }

    @PutMapping("/{id}")
        public ResponseEntity<JobOpport> updateJobOpport(@PathVariable("id") int id, @RequestBody JobOpport jobOpport) {
            JobOpport updatedJobOpport = jobOpportServiceInterface.updateJobOpport(id, jobOpport);
            if (updatedJobOpport != null) {
                return new ResponseEntity<>(updatedJobOpport, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteJobOpport(@PathVariable("id") int id) {
            jobOpportServiceInterface.deleteJobOpport(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping("/getalljob")
        public ResponseEntity<List<JobOpport>> getAllJobOpports() {
            List<JobOpport> jobOpports = jobOpportServiceInterface.getAllJobOpports();
            return new ResponseEntity<>(jobOpports, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<JobOpport> getJobOpportById(@PathVariable("id") int id) {
            JobOpport jobOpport = jobOpportServiceInterface.getJobOpportById(id);
            if (jobOpport != null) {
                return new ResponseEntity<>(jobOpport, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }


