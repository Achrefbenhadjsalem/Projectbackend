package com.test.COCONSULT.Tasks;

import com.mysql.cj.log.Log;
import com.test.COCONSULT.Controllers.PointageController;
import com.test.COCONSULT.DTO.TypePrime;
import com.test.COCONSULT.Entity.Prime;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Reposotories.PrimeRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import com.test.COCONSULT.ServiceIMP.SalaireServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTasks {

    private final UserRepository userRepository;

    @Autowired
    PointageController pointageController;

    @Autowired
    SalaireServiceIMP salaireServiceIMP;

    @Autowired
    PrimeRepository primeRepository;

    public ScheduledTasks(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "15 0 0 1 * *")
    public void addMonthlyConge() {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            user.addMonthlyConge();
            userRepository.save(user);
        }
    }

    @Scheduled(cron = "15 3 10 * * *")
    public void sendReminderEmails10() {
        //console log
        System.out.println("Sending reminder emails");
        pointageController.sendReminderEmail();
    }



    @Scheduled(cron = "0 0 0 25 12 ?") // Exécute le 25 décembre de chaque année
    public void giveHolidayBonus() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Prime prime = new Prime();
            prime.setMontant(100); // Montant spécial pour Noël
            prime.setUser(user);
            prime.setType(TypePrime.Fetes_nationnale); // Ajustez selon le cas
            primeRepository.save(prime);
        }
    }


    @Scheduled(cron = "15 3 14 * * *")
    public void sendReminderEmails12() {
        //console log
        System.out.println("Sending reminder emails");
        pointageController.sendReminderEmail();
    }

    @Scheduled(cron = "0 0 0 1 * *") // Executes at midnight on the 1st of every month
    public void addSessionDurationPrime() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            // Check if the user has a session duration of more than 160 hours in the last month
            Duration sessionDurationLastMonth = calculateSessionDurationLastMonth(user);
            if (sessionDurationLastMonth.toHours() > 160) {
                // Add prime
                Prime prime = new Prime();
                prime.setMontant(300); // Adjust the prime amount as needed
                prime.setUser(user);
                prime.setType(TypePrime.Rendement); // Adjust the prime type
                primeRepository.save(prime);
            }

            user.setSessionDuration(0L); // Reset session duration
            userRepository.save(user);
        }
    }
    private Duration calculateSessionDurationLastMonth(User user) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastMonthStart = now.minusMonths(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime lastMonthEnd = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);

        // Retrieve session duration for the last month
        Duration sessionDurationLastMonth = Duration.ofMillis(user.getSessionDuration());
        return sessionDurationLastMonth;
    }

    @Scheduled(cron = "30 0 0 1 * *")
    public void addSalaire() {
        salaireServiceIMP.addSalaireToAllUsers();
    }

}
