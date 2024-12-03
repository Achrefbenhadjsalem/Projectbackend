package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Meetings;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.MeetingInterface;
import com.test.COCONSULT.Reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Meeting")
@CrossOrigin("*")
public class MeetingControlleur {
    @Autowired
    MeetingInterface meetingInterface;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/addmeet")
    public Meetings AddMeeting(@RequestBody Meetings meet) {
        return meetingInterface.AddMeeting(meet);
    }
    @GetMapping("/getMeetingById/{idMeeting}")
    public Meetings getMeetingById(@PathVariable("idMeeting") Integer idMeeting) {
        return meetingInterface.getMeetingById(idMeeting);
    }
    @GetMapping("/getAllMeetings")
    public List<Meetings> getAllMeetings() {
        return meetingInterface.getAllMeetings();
    }


    @DeleteMapping("/deleteMeetingByID/{idMeeting}")
    public void deleteMeetingByID(@PathVariable("idMeeting") Integer idMeeting) {
        meetingInterface.deleteMeetingByID(idMeeting);
    }
    @PutMapping("/editMeetingByID/{idMeeting}")
    public Meetings editMeetingByID(@PathVariable("idMeeting") Integer idMeeting,@RequestBody Meetings updateMeeting) {
        return meetingInterface.editMeetingByID(idMeeting, updateMeeting);
    }

    @PostMapping("/addMeetingandAffecteraunprojet/{projectTitle}")
    public Meetings addMeetingandAffecteraunprojet(@RequestBody Meetings meet,@PathVariable("projectTitle") String projectTitle) {
        return meetingInterface.addMeetingandAffecteraunprojet(meet, projectTitle);
    }
    @RequestMapping(value = "/affecterUserAmeet/{idMeeting}/{username}", method = {RequestMethod.POST, RequestMethod.PUT})
    public void affecterUserAmeet(@PathVariable("idMeeting") Integer idMeeting, @PathVariable("username") String username) {
        // Find the meeting
        Meetings meeting = meetingInterface.getMeetingById(idMeeting);
        if (meeting == null) {
            // Handle case where meeting is not found
            // You may throw an exception or log an error
            return;
        }

        // Affect the user to the meeting
        meetingInterface.affecterUserAmeet(idMeeting, username);

        // Find the user
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            // Handle case where user is not found
            // You may throw an exception or log an error
            return;
        }

        // Send the meeting assigned email
        meetingInterface.sendMeetingAssignedEmail(meeting, user.getEmail());
    }

    @GetMapping("/getUsersByMeetingId/{idMeeting}")
    public List<User> getUsersByMeetingId(@PathVariable("idMeeting") Integer idMeeting) {
        return meetingInterface.getUsersByMeetingId(idMeeting);
    }
}
