package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Activity;
import com.test.COCONSULT.Interfaces.ActivityInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Activity")
@CrossOrigin("*")
public class ActivityControlleur {
    @Autowired
    ActivityInterface activityInterface;
    @PostMapping("/addActivity/{projectTitle}")
    public Activity addActivity(@RequestBody Activity Act,@PathVariable("projectTitle") String projectTitle) {
        return activityInterface.addActivity(Act, projectTitle);
    }

    @GetMapping("/getActivity/{IdActivity}")
    public Activity getActivityBayID(@PathVariable("IdActivity") Integer IdActivity) {
        return activityInterface.getActivityByID(IdActivity);
    }

    @DeleteMapping("/DeleteActivityByID/{ActID}")
    public void DeleteActivityByID(@PathVariable("ActID") Integer ActID) {
        activityInterface.DeleteActivityByID(ActID);
    }

    @PutMapping("/editActivityByID/{ActID}")
    public Activity editActivityByID(@PathVariable("ActID") Integer ActID,@RequestBody Activity updatedActivity) {
        return activityInterface.editActivityByID(ActID, updatedActivity);
    }

    @GetMapping("/getAllActivities")
    public List<Activity> getAllActivities() {
        return activityInterface.getAllActivities();
    }
}