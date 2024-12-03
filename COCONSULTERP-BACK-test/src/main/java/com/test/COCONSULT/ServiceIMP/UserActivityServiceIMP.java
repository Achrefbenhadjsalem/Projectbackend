package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.UserActivity;
import com.test.COCONSULT.Interfaces.UserActivityServiceInterface;
import com.test.COCONSULT.Reposotories.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActivityServiceIMP implements UserActivityServiceInterface {

    @Autowired
    private UserActivityRepository userActivityRepository;
    @Override
    public void addActivity(UserActivity userActivity) {
        userActivityRepository.save(userActivity);
    }

    @Override
    public void deleteActivity(int idActivity) {
        userActivityRepository.deleteById(idActivity);
    }

    @Override
    public UserActivity getActivityById(int idActivity) {
        return userActivityRepository.findById(idActivity).get();
    }

    @Override
    public UserActivity updateActivity(UserActivity userActivity) {
        return userActivityRepository.save(userActivity);
    }
}
