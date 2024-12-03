package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Pointage;
import com.test.COCONSULT.Entity.RappelPointage;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Reposotories.RappelPointageRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import com.test.COCONSULT.ServiceIMP.PointageServiceIMP;
import com.test.COCONSULT.Services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/api/pointage")
public class PointageController {
    @Autowired
    PointageServiceIMP pointageServiceIMP;

    @Autowired
    MailSenderService mailSenderService;


    @Autowired
    UserRepository userRepository;

    @Autowired
    RappelPointageRepository rappelPointageRepository;

    @PostMapping("/users/{userId}/pointage/add")
    public ResponseEntity<?> addPointageForUser(@PathVariable("userId") long userId) {
        try {
            pointageServiceIMP.addPointageForUser(userId);
            return ResponseEntity.ok("Pointage added successfully for user ID: " + userId);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String generateVerificationCode() {
        // Generate a random 4-digit verification code
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        return String.valueOf(code);
    }

    @GetMapping("/verification/")
    public void sendReminderEmail(){
        List<User> users = userRepository.findAll();

        String newLine = "<br>";
        String url = "http://localhost:4200/#/pointage/verification";
        String subject = "Reminder: Verify your pointage";
        String verificationCode = generateVerificationCode();


        for (User user : users) {
            String htmlMessage ="<div style='border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;'>"
                    + "Rappel pointage" + newLine
                    + "use the link to verify : " + newLine
                    + "<a href='" + url + "'>" + url + "</a>" + newLine
                    + "<strong>Verification Code:</strong> " + verificationCode + newLine
                    + "</div>";

            RappelPointage rappelPointage = new RappelPointage();
            rappelPointage.setDateRappel(new Date());
            rappelPointage.setUser(user);
            rappelPointage.setVerificationCode(verificationCode);

            rappelPointageRepository.save(rappelPointage);
                try {
                        mailSenderService.send(user.getEmail(), subject, htmlMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                }
        }
    }

    @PostMapping("/verify-reminder/{id}/{code}")
    public void verifyReminder(@PathVariable("id") int id, @PathVariable("code") String verificationCode) {
        RappelPointage rappelPointage = rappelPointageRepository.findByUser_Id((long) id);
        if (rappelPointage == null) {
            return;
        }
        if (!rappelPointage.getVerificationCode().equals(verificationCode)) {
            System.out.println("Verification code is incorrect");
            return;
        }
        rappelPointageRepository.deleteById(rappelPointage.getId());
    }

}
