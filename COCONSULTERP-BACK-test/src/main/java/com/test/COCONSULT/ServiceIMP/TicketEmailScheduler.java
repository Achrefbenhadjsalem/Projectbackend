package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.TicketStatus;
import com.test.COCONSULT.Entity.Tickets;
import com.test.COCONSULT.ServiceIMP.TicketServiceImp;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketEmailScheduler {

    private final TicketServiceImp ticketService;

    public TicketEmailScheduler(TicketServiceImp ticketService) {
        this.ticketService = ticketService;
    }

    @Scheduled(fixedRate = 180000) // Exécute toutes les 3 minutes
    public void sendEmailToAssignedUsers() {
        List<Tickets> openTickets = ticketService.getAllTicketsByStatus(TicketStatus.Open);

        for (Tickets ticket : openTickets) {
            ticketService.sendTicketAssignedEmail(ticket, ticket.getUser().getEmail());
        }
    }
}
