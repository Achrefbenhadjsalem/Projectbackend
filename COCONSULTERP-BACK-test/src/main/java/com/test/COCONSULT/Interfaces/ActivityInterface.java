package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Activity;

import java.util.List;

public interface ActivityInterface {
    Activity addActivity(Activity Act,String projectTitle);

    Activity getActivityByID(Integer ActID);

    void DeleteActivityByID(Integer ActID);

    Activity editActivityByID(Integer ActID, Activity updatedActivity);

    public List<Activity> getAllActivities();













}
