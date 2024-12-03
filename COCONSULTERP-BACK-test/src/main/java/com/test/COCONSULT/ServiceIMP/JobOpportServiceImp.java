package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.JobOpport;
import com.test.COCONSULT.Interfaces.JobOpportServiceInterface;
import com.test.COCONSULT.Reposotories.CandidatRepository;
import com.test.COCONSULT.Reposotories.JobOpportRepository;
import com.test.COCONSULT.Services.MailSenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class JobOpportServiceImp implements JobOpportServiceInterface {

    @Autowired
    JobOpportRepository jobOpportRepository;
    @Autowired
    MailSenderService mailSenderService;
    @Autowired
    CandidatRepository candidatRepository;



    @Override
    public void addJobOpportunity(JobOpport jobOpport) {
        JobOpport savedJobOpport = jobOpportRepository.save(jobOpport);
        String title = savedJobOpport.getTitre();

        // Extract keywords from the title
        String[] keywords = title.split("\\s+");

        // Find candidates whose competencies match any of the keywords
        List<Candidat> relevantCandidates = new ArrayList<>();
        for (String keyword : keywords) {
            relevantCandidates.addAll(candidatRepository.findByCompetenceContaining(keyword));
        }

        // Send emails to relevant candidates
        sendEmailsToCandidates(relevantCandidates, savedJobOpport);
    }

    private void sendEmailsToCandidates(List<Candidat> candidates, JobOpport jobOpport) {
        for (Candidat candidate : candidates) {
            String subject = "New Job Opportunity: " + jobOpport.getTitre();
            String message = "Dear " + candidate.getNom() + ",\n\n"
                    + "A new job opportunity matching your competencies has been posted: " + jobOpport.getTitre() + ".\n\n"
                    + "Description: " + jobOpport.getDescription() + "\n\n"
                    + "Qualifications: " + jobOpport.getQualifications() + "\n\n"
                    + "Location: " + jobOpport.getLieu() + "\n\n"
                    + "Apply now!";
            // Send email
            try {
                // Send email
                mailSenderService.send(candidate.getEmail(), subject, message);
            } catch (MessagingException e) {
                // Log the error
                e.printStackTrace(); // Or use your logging framework
                // Handle the exception as needed
            }
        }
    }


    @Override
    public JobOpport updateJobOpport(int id, JobOpport jobOpport) {
        if (jobOpportRepository.existsById(id)) {
            jobOpport.setId_offre(id);
            return jobOpportRepository.save(jobOpport);
        }
        return null;
    }

    @Override
    public void deleteJobOpport(int id) {
        jobOpportRepository.deleteById(id);
    }

    @Override
    public List<JobOpport> getAllJobOpports() {
        return jobOpportRepository.findAll();
    }

    @Override
    public JobOpport getJobOpportById(int id) {
        return jobOpportRepository.findById(id);
    }
}

