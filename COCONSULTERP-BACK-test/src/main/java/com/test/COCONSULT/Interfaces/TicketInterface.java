package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.DTO.TicketStatus;
import com.test.COCONSULT.Entity.Activity;
import com.test.COCONSULT.Entity.Meetings;
import com.test.COCONSULT.Entity.Tickets;

import java.util.List;

public interface TicketInterface {
    Tickets addTicket(Tickets tick);

    Tickets addTicketandaffecteruser(Tickets tick, String username);

    void affecterTicketAuser(Integer idTicket, String username);

    Tickets getTicketByID(Integer idTicket);

    void deleteTicketById(Integer idTicket);

    Tickets editTicketByID(Integer idTicket, Tickets updateTicket);

    public List<Tickets> getAllTickets();

    String getAssignedUserNameByTicketId(Integer idTicket);


    Tickets cleanOldTickets();

    List<Tickets> getAllOpenTickets();


    void sendTicketAssignedEmail(Tickets ticket, String userEmail);

    List<Tickets> getAllTicketsByStatus(TicketStatus status);

}
