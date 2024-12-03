package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.UserActivity;

public interface UserActivityServiceInterface {
    public void addActivity(UserActivity userActivity);
    public void deleteActivity(int idActivity);
    public UserActivity getActivityById(int idActivity);

    public UserActivity updateActivity(UserActivity userActivity);
}
