package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.ActivitySalesTeam;
import com.test.COCONSULT.Entity.Contract;

import java.util.List;

public interface ActivitySalesTeamService {
    List<ActivitySalesTeam> retrieveActivitySalesTeam();
    ActivitySalesTeam updateActivitySalesTeam(ActivitySalesTeam activitySalesTeam);
    ActivitySalesTeam addActivitySalesTeam(ActivitySalesTeam activitySalesTeam);

    ActivitySalesTeam retrieveActivitySalesTeam(Long idActSale);

    public List<ActivitySalesTeam> getActivitySalesTeamByClass(String classSalesTeam) ;

    public ActivitySalesTeam updateStatus(Long idActSale);

    ActivitySalesTeam addActivityAffectRep(ActivitySalesTeam activitySalesTeam, Long repertoireId) ;

     ActivitySalesTeam addActivityAffectRepSendSMS(ActivitySalesTeam activitySalesTeam, Long repertoireId) ;



    void removeActivitySalesTeam(Long idActSale);
}
