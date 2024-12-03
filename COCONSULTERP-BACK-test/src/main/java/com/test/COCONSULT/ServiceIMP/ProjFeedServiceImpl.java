package com.test.COCONSULT.ServiceIMP;


import com.test.COCONSULT.Entity.ProjFeed;
import com.test.COCONSULT.Interfaces.ProjFeedService;
import com.test.COCONSULT.Reposotories.ProjFeedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProjFeedServiceImpl implements ProjFeedService {
    ProjFeedRepository projFeedRepository;

    @Override
    public List<ProjFeed> retrieveProjFeeds() {
        return projFeedRepository.findAll();
    }

    @Override
    public ProjFeed updateProjFeed(ProjFeed projFeed) {
        return projFeedRepository.save(projFeed);
    }

    @Override
    public ProjFeed addProjFeed(ProjFeed projFeed) {
        return projFeedRepository.save(projFeed);
    }

    @Override
    public ProjFeed retrieveProjFeed(Long idProjFeed) {
        return projFeedRepository.findById(idProjFeed).orElse(null);
    }

    @Override
    public void removeProjFeed(Long idProjFeed) {
        projFeedRepository.deleteById(idProjFeed);
    }

    @Override
    public List<ProjFeed> getProjFeedsForProject(Long projectId) {
        return projFeedRepository.findProjFeedByIdPjtFeed(projectId);
    }
    @Override
    public List<ProjFeed> getProjFeedWithProjects(Long projectId) {
        return projFeedRepository.findProjFeedByIdPjtFeed(projectId);
    }

    @Override
    public List<ProjFeed> getProjFeedsUpdatedAfterDate(String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return projFeedRepository.findByTimeUpdAfter(parsedDate);
    }


    @Override
    public List<ProjFeed> getLastProjFeeds(int limit) {
        return projFeedRepository.findTopNByOrderByTimeUpdDesc();
    }

    @Override
    public long getTotalProjFeedsCount() {
        return projFeedRepository.count();
    }
}