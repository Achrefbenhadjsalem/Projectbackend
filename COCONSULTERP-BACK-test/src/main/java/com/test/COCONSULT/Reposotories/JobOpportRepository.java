package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.JobOpport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOpportRepository extends JpaRepository<JobOpport,Integer> {
    JobOpport findById(int id_offre);


}
