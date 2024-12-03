package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.DTO.TicketStatus;
import com.test.COCONSULT.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Tickets,Integer> {
    Tickets findTicketsByIdTicket(Integer idTicket);

    void deleteTicketsByIdTicket(Integer idTicket);

    @Query("SELECT t FROM Tickets t WHERE t.ticketStatus = 'Open'")
    List<Tickets> findAllOpenTickets();

    List<Tickets> findAllByTicketStatus(TicketStatus status);








}
