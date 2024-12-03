package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.JobOpport;

import java.util.List;

public interface JobOpportServiceInterface {

        public void addJobOpportunity(JobOpport jobOpport);
        JobOpport updateJobOpport(int id, JobOpport jobOpport);
        void deleteJobOpport(int id);
        List<JobOpport> getAllJobOpports();
        JobOpport getJobOpportById(int id);
    }


