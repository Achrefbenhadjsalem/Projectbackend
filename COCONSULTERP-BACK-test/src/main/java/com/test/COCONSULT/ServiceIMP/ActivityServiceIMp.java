package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Activity;
import com.test.COCONSULT.Entity.Projets;
import com.test.COCONSULT.Interfaces.ActivityInterface;
import com.test.COCONSULT.Reposotories.ActivityRepository;
import com.test.COCONSULT.Reposotories.ProjetsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class ActivityServiceIMp implements ActivityInterface {
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    ProjetsRepository projetRepository;

    @Override
    public Activity addActivity(Activity Act, String projectTitle) {
        Projets projet=projetRepository.getProjetByProjetTitle(projectTitle);
        activityRepository.save(Act);
        Act.setProjet(projet);
        return activityRepository.save(Act);

    }

    @Override
    public Activity getActivityByID(Integer ActID) {
        return activityRepository.findActivityByIdActivity(ActID);
    }

    @Override
    @Transactional
    public void DeleteActivityByID(Integer ActID) {
        activityRepository.deleteActivityByIdActivity(ActID);
    }

    @Override
    public Activity editActivityByID(Integer ActID, Activity updatedActivity) {
        Activity existingActivity = activityRepository.findActivityByIdActivity(ActID);
        if (existingActivity != null) {
            existingActivity.setNbreOfTask(updatedActivity.getNbreOfTask());
            existingActivity.setTaskType(updatedActivity.getTaskType());
            existingActivity.setProjet(updatedActivity.getProjet());
            existingActivity.setTickets(updatedActivity.getTickets());
            return activityRepository.save(existingActivity);
        } else {
            System.out.println("Activity dos not exist");
            return null;
        }


    }

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
}
