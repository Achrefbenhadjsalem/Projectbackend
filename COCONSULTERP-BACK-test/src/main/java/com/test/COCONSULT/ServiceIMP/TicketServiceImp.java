    package com.test.COCONSULT.ServiceIMP;

    import com.test.COCONSULT.DTO.TicketStatus;
    import com.test.COCONSULT.Entity.Tickets;
    import com.test.COCONSULT.Entity.User;
    import com.test.COCONSULT.Interfaces.TicketInterface;
    import com.test.COCONSULT.Reposotories.TicketRepository;
    import com.test.COCONSULT.Reposotories.UserRepository;
    import com.test.COCONSULT.Services.MailSenderService;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import javax.transaction.Transactional;
    import java.util.List;
    import com.test.COCONSULT.Services.TwilioService;


    @Slf4j
    @Service
    public class TicketServiceImp implements TicketInterface {
        @Autowired
        TicketRepository ticketRepository;
        @Autowired
        UserRepository userRepository;
        @Autowired
        private MailSenderService mailSenderService;
        @Autowired
        private TwilioService twilioService; // Ajout du service Twilio



        @Override
        public Tickets addTicket(Tickets tick) {
            return ticketRepository.save(tick);
        }

        @Override
        @Transactional
        public Tickets addTicketandaffecteruser(Tickets tick, String username) {
            User user = userRepository.findByUsername(username).orElse(null);
            ticketRepository.save(tick);
            tick.setUser(user);
            if (user != null) {
                String messageBody = "Dear User,\n\n" + tick.getUser().getName()+" \n" +
                        "A new ticket has been assigned to you.\n\n" +
                        "Ticket Title: " + tick.getTickettitle() + "\n" +
                        "Priority: " + tick.getTicketPriority() + "\n" +
                        "Content: " + tick.getTicketContent() + "\n\n" +
                        "Regards,\nYour Company";
               // twilioService.sendSMS("+21651775223", messageBody);
            }


            return ticketRepository.save(tick);

        }
        @Override
        public void affecterTicketAuser(Integer idTicket, String username) {
            Tickets tickets=ticketRepository.findTicketsByIdTicket(idTicket);
            User user=userRepository.findByUsername(username).orElse(null);
            tickets.setUser(user);
            userRepository.save(user);

        }


        @Override
        public Tickets getTicketByID(Integer idTicket) {
            return ticketRepository.findTicketsByIdTicket(idTicket);
        }

        @Override
        @Transactional
        public void deleteTicketById(Integer idTicket) {
            ticketRepository.deleteTicketsByIdTicket(idTicket);
        }

        @Override
        public Tickets editTicketByID(Integer idTicket, Tickets updateTicket) {
            Tickets existingTicket = ticketRepository.findTicketsByIdTicket(idTicket);
            if (existingTicket != null){
                existingTicket.setTicketContent(updateTicket.getTicketContent());
                existingTicket.setTicketStatus(updateTicket.getTicketStatus());
                existingTicket.setTicketPriority(updateTicket.getTicketPriority());
                existingTicket.setTickettitle(updateTicket.getTickettitle());
                existingTicket.setDateAssigned(updateTicket.getDateAssigned());
                return ticketRepository.save(existingTicket);
            }
            System.out.println("Ticket dos not existe anymore");
            return null;
        }

        @Override
        public List<Tickets> getAllTickets() {
            return ticketRepository.findAll();
        }

        @Override
        public String getAssignedUserNameByTicketId(Integer idTicket) {
            Tickets ticket = ticketRepository.findTicketsByIdTicket(idTicket);
            if (ticket != null && ticket.getUser() != null) {
                return ticket.getUser().getName();
            }
            return "No user assigned or ticket not found";
        }
        public void sendTicketAssignedEmail(Tickets ticket, String userEmail) {
            log.info("Attempting to send email to: {}", userEmail);

            if (userEmail == null || userEmail.isEmpty()) {
                log.error("Email not sent: User email is null or empty.");
                return;
            }

            String subject = " New Ticket Assigned: " + ticket.getTickettitle();
            String body = "Dear User,\n\n" + ticket.getUser().getName()+" \n" +
                    "A new ticket has been assigned to you.\n\n" +
                    "Ticket Title: " + ticket.getTickettitle() + "\n" +
                    "Priority: " + ticket.getTicketPriority() + "\n" +
                    "Content: " + ticket.getTicketContent() + "\n\n" +
                    "Regards,\nYour Company";

            try {
                mailSenderService.send(userEmail, subject, body);
                log.info("Email successfully sent to: {}", userEmail);
            } catch (Exception e) {
                log.error("Error sending ticket assigned email: ", e);
            }
        }

        @Override
        public List<Tickets> getAllTicketsByStatus(TicketStatus status) {
            return ticketRepository.findAllByTicketStatus(status);
        }

        @Override
        public Tickets cleanOldTickets() {
            return null;
        }

        @Override
        public List<Tickets> getAllOpenTickets() {
            return ticketRepository.findAllOpenTickets();

        }


    }
