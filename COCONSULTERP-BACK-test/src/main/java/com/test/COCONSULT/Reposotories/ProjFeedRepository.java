package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.ProjFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjFeedRepository extends JpaRepository<ProjFeed, Long> {
    //List<ProjFeed> findBy(Long idProjet);
    List<ProjFeed> findProjFeedByIdPjtFeed(Long projectId);

    List<ProjFeed> findByTimeUpdAfter(LocalDate date);

    List<ProjFeed> findTopNByOrderByTimeUpdDesc();
}