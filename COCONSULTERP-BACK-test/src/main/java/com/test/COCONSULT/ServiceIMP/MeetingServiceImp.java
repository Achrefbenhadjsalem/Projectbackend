package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Meetings;
import com.test.COCONSULT.Entity.Projets;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.MeetingInterface;
import com.test.COCONSULT.Reposotories.MeetingsRepository;
import com.test.COCONSULT.Reposotories.ProjetsRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import com.test.COCONSULT.Services.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class MeetingServiceImp implements MeetingInterface {
    @Autowired
    MeetingsRepository meetingsRepository;
    @Autowired
    ProjetsRepository projetRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private MailSenderService mailSenderService;


    @Override
    public Meetings AddMeeting(Meetings meet) {
        return meetingsRepository.save(meet);
    }

    @Override
    public Meetings getMeetingById(Integer idMeeting) {
        return meetingsRepository.findMeetingsByIdMeeting(idMeeting);
    }

    @Override
    @Transactional
    public void deleteMeetingByID(Integer idMeeting) {
        meetingsRepository.deleteMeetingsByIdMeeting(idMeeting);
    }

    @Override
    public Meetings editMeetingByID(Integer idMeeting, Meetings updateMeeting) {
        Meetings existingMeet = meetingsRepository.findMeetingsByIdMeeting(idMeeting);
        if (existingMeet != null) {
            existingMeet.setDateMeeting(updateMeeting.getDateMeeting());
            existingMeet.setTypeMeet(updateMeeting.getTypeMeet());
            existingMeet.setProjets(updateMeeting.getProjets());
            return meetingsRepository.save(existingMeet);

        } else {
            System.out.println("Meeting dos not exist");
            return null;
        }

    }

    @Override
    public Meetings addMeetingandAffecteraunprojet(Meetings meet, String projectTitle) {
        Projets projet=projetRepository.getProjetByProjetTitle(projectTitle);
        meetingsRepository.save(meet);
        meet.setProjets(projet);
        return meetingsRepository.save(meet);
    }

    @Override
    public List<Meetings> getAllMeetings() {
        return meetingsRepository.findAll();
    }

    @Override
    public void affecterUserAmeet(Integer idMeeting, String username) {
        // Find the meeting
        Meetings meeting = meetingsRepository.findMeetingsByIdMeeting(idMeeting);
        if (meeting == null) {
            // Handle case where meeting is not found
            // You may throw an exception or log an error
            return;
        }

        // Find the user
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            // Handle case where user is not found
            // You may throw an exception or log an error
            return;
        }

        // Add the meeting to the user's meetings
        user.getMeetings().add(meeting);
        userRepository.save(user);
    }

    @Override
    public List<User> getUsersByMeetingId(Integer idMeeting) {
        return userRepository.findUsersByMeetingId(idMeeting);

    }

    @Override
    public void sendMeetingAssignedEmail(Meetings meeting, String userEmail) {
        String subject = "New Meeting Assigned: " + meeting.getTypeMeet();
        String body = "Dear User,\n\n" +
                "You have been assigned to a new meeting.\n\n" +
                "Meeting Type: " + meeting.getTypeMeet() + "\n" +
                "Meeting Date: " + meeting.getDateMeeting() + "\n\n" +
                "Please use the following link to view the meeting details: http://localhost:4200/#/user_dashboard/MeetingsListe\n\n" +
                "Regards,\nYour Company";

        try {
            mailSenderService.send(userEmail, subject, body);
        } catch (Exception e) {
            log.error("Error sending meeting assigned email: " + e.getMessage());
        }
    }


}

