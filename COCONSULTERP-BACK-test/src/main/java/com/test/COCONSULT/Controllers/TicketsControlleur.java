package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.DTO.TicketStatus;
import com.test.COCONSULT.Entity.Tickets;
import com.test.COCONSULT.Interfaces.FileStorageService;
import com.test.COCONSULT.Interfaces.TicketInterface;
import com.test.COCONSULT.Services.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ticket")
@CrossOrigin("*")
public class TicketsControlleur {
    @Autowired
    TicketInterface ticketInterface;

    @Autowired
    private FileStorageService fileStorageService;
    /*@Autowired
    private TwilioService twilioService;
*/

    @GetMapping("/getAllTickets")
    public List<Tickets> getAllTickets() {
        return ticketInterface.getAllTickets();
    }


    @PostMapping("/addTicket")
    public Tickets addTicket(@RequestBody Tickets tick) {
        return ticketInterface.addTicket(tick);
    }
    @PostMapping("/addTicketandaffecteruser/{username}")
    public Tickets addTicketandaffecteruser(@RequestBody Tickets tick,@PathVariable("username") String username) {
        Tickets ticket = ticketInterface.addTicketandaffecteruser(tick, username);
        if (ticket != null && ticket.getUser() != null) {
            ticketInterface.sendTicketAssignedEmail(ticket, ticket.getUser().getEmail());
        }

        return ticket;
    }
    @PutMapping("/affecterTicketAuser/{idTicket}/{username}")
    public void affecterTicketAuser(@PathVariable("idTicket") Integer idTicket,@PathVariable("username") String username) {

        ticketInterface.affecterTicketAuser(idTicket, username);
    }


    @GetMapping("/getTicketByID/{idTicket}")
    public Tickets getTicketByID(@PathVariable("idTicket") Integer idTicket) {
        return ticketInterface.getTicketByID(idTicket);
    }
    @DeleteMapping("/deleteTicketById/{idTicket}")
    public void deleteTicketById(@PathVariable("idTicket") Integer idTicket) {
        ticketInterface.deleteTicketById(idTicket);
    }
    @PutMapping("/editTicketByID/{idTicket}")
    public Tickets editTicketByID(@PathVariable("idTicket") Integer idTicket,@RequestBody Tickets updateTicket) {
        return ticketInterface.editTicketByID(idTicket, updateTicket);
    }
    @GetMapping("/getAssignedUserNameByTicketId/{idTicket}")
    public String getAssignedUserNameByTicketId(@PathVariable("idTicket") Integer idTicket) {
        return ticketInterface.getAssignedUserNameByTicketId(idTicket);
    }
    @GetMapping("/getTicketsByStatus/{status}")
    public List<Tickets> getTicketsByStatus(@PathVariable("status") TicketStatus status) {
        return ticketInterface.getAllTicketsByStatus(status);
    }
    @PutMapping("/updateTicketStatus/{idTicket}")
    public ResponseEntity<Tickets> updateTicketStatus(@PathVariable("idTicket") Integer idTicket, @RequestBody String status) {
        // Convertir la chaîne en TicketStatus
        TicketStatus ticketStatus;
        try {
            ticketStatus = TicketStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }

        Tickets ticket = ticketInterface.getTicketByID(idTicket);
        if (ticket != null) {
            ticket.setTicketStatus(ticketStatus);
            ticketInterface.editTicketByID(idTicket, ticket);
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
