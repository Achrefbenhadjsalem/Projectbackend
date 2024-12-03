package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.TicketStatus;
import com.test.COCONSULT.Entity.Tickets;
import com.test.COCONSULT.Interfaces.TicketInterface;
import com.test.COCONSULT.Services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduledEmailService {

    @Autowired
    private TicketInterface ticketInterface;

    @Autowired
    private MailSenderService mailSenderService;
    @Scheduled(cron = "0 0 */6 * * *") // Expression cron pour une exécution toutes les 6 heures
  //  @Scheduled(fixedRate = 180000) // 3 minutes
    public void sendEmailToUsersWithOpenTickets() {
        List<Tickets> openTickets = ticketInterface.getAllOpenTickets();

        for (Tickets ticket : openTickets) {
            String userEmail = ticket.getUser().getEmail();
            String subject = "Ticket en attente+ de résolution : " + ticket.getTickettitle() + " (ID: " + ticket.getIdTicket() + ")";
            String body = "Cher utilisateur,\n\n" +
                    "Vous avez un ticket en attente de résolution. Merci de le traiter dès que possible.\n\n" +
                    "Titre du ticket : " + ticket.getTickettitle() + "\n" +
                    "ID du ticket : " + ticket.getIdTicket() + "\n\n" +
                    "Nous vous remercions pour votre attention.\n" +
                    "Cordialement,\nVotre entreprise";

            try {
                mailSenderService.send(userEmail, subject, body);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
    @Scheduled(fixedRate = 60000) // 2 minutes
    public void deleteCancelledTickets() {
        List<Tickets> cancelledTickets = ticketInterface.getAllTicketsByStatus(TicketStatus.Cancelled);
        LocalDateTime twoMinutesAgo = LocalDateTime.now().minusMinutes(1);

        for (Tickets ticket : cancelledTickets) {
            LocalDateTime ticketDateAssigned = ticket.getDateAssigned().atStartOfDay();
            if (ticketDateAssigned.isBefore(twoMinutesAgo)) {
                ticketInterface.deleteTicketById(ticket.getIdTicket());
            }
        }
    }
}
