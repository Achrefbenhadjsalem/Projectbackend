package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.ClassSalesTeam;
import com.test.COCONSULT.DTO.Status;
import com.test.COCONSULT.Entity.ActivitySalesTeam;
import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Repertoire;
import com.test.COCONSULT.Interfaces.ActivitySalesTeamService;
import com.test.COCONSULT.Reposotories.ActivitySalesTeamRepository;
import com.test.COCONSULT.Reposotories.RepertoireRepository;
import com.test.COCONSULT.Services.SMSServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivitySalesTeamImpl implements ActivitySalesTeamService {
    @Autowired
    ActivitySalesTeamRepository activitySalesTeamRepository;
    @Autowired
    RepertoireRepository repertoireRepository;

    @Autowired
    SMSServices smsServices;

    @Override
    public List<ActivitySalesTeam> retrieveActivitySalesTeam() {
        return activitySalesTeamRepository.findAll();
    }

    @Override
    public ActivitySalesTeam updateActivitySalesTeam(ActivitySalesTeam activitySalesTeam) {
        return activitySalesTeamRepository.save(activitySalesTeam);
    }

    @Override
    public ActivitySalesTeam addActivitySalesTeam(ActivitySalesTeam activitySalesTeam) {
        return activitySalesTeamRepository.save(activitySalesTeam);
    }


    @Override
    public ActivitySalesTeam retrieveActivitySalesTeam(Long idActSale) {
        Optional<ActivitySalesTeam> ActivitySalesTeamOptional = activitySalesTeamRepository.findById(idActSale);
        return ActivitySalesTeamOptional.orElse(null);
    }

    @Override
    public List<ActivitySalesTeam> getActivitySalesTeamByClass(String classSalesTeam) {
        return activitySalesTeamRepository.findByClassSalesTeam(ClassSalesTeam.valueOf(classSalesTeam));

    }


    @Override
    public ActivitySalesTeam updateStatus(Long idActSale) {
        // Retrieve the activity from the database by its ID
        Optional<ActivitySalesTeam> optionalActivity = activitySalesTeamRepository.findById(idActSale);

        if (optionalActivity.isPresent()) {
            ActivitySalesTeam activitySalesTeam = optionalActivity.get();

            // Retrieve the current status of the activity
            Status currentStatus = activitySalesTeam.getStatus();

            // Toggle the status between 'DONE' and 'WAITING'
            if (currentStatus == Status.WAITING) {
                activitySalesTeam.setStatus(Status.DONE);
            } else {
                activitySalesTeam.setStatus(Status.WAITING);
            }

            // Update the activity in the database
            return activitySalesTeamRepository.save(activitySalesTeam);
        } else {
            // Return null if the activity is not found
            return null;
        }
    }

    @Override
    public ActivitySalesTeam addActivityAffectRep(ActivitySalesTeam activitySalesTeam, Long repertoireId) {
        Repertoire repertoire = repertoireRepository.findById(repertoireId).orElse(null);
        if (repertoire != null) {
            activitySalesTeam.setRepertoireee(repertoire);
            // Automatically set repertoireContact to the Contact attribute of Repertoire
            activitySalesTeam.setRepertoireContact(repertoire.getContact());
            return activitySalesTeamRepository.save(activitySalesTeam);
        } else {
            return null;
        }
    }

    @Override
    public ActivitySalesTeam addActivityAffectRepSendSMS(ActivitySalesTeam activitySalesTeam, Long repertoireId) {
        Repertoire repertoire = repertoireRepository.findById(repertoireId).orElse(null);
        if (repertoire != null) {
            activitySalesTeam.setRepertoireee(repertoire);
            // Automatically set repertoireContact to the Contact attribute of Repertoire
            activitySalesTeam.setRepertoireContact(repertoire.getContact());

            String messageBody = "Dear User,\n\n" + activitySalesTeam.getRepertoireee().getContact() + " \n" +
                    "There is some news .\n\n" +
                    "Content: " + activitySalesTeam.getDescription() + "\n" +
                    "Heure Start: " + activitySalesTeam.getHeureStart() + "\n" +
                    "Heure End: " + activitySalesTeam.getHeureEnd() + "\n\n" +
                    "\nYour are Welcome ";

            smsServices.sendSms(repertoire.getNumTel(), messageBody);
        }
        return activitySalesTeamRepository.save(activitySalesTeam);
    }





    @Override
    public void removeActivitySalesTeam(Long idActSale) {
        activitySalesTeamRepository.deleteById(idActSale);
    }
}
