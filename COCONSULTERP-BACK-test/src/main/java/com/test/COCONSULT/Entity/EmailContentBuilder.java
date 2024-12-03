package com.test.COCONSULT.Entity;

import com.test.COCONSULT.Entity.Tickets;

public class EmailContentBuilder {

    public static String buildTicketReminderEmailContent(Tickets ticket) {
        StringBuilder content = new StringBuilder();

        content.append("Dear ").append(ticket.getUser().getName()).append(",\n\n");
        content.append("This is a reminder for the ticket with ID ").append(ticket.getIdTicket()).append(".\n");
        content.append("Ticket Title: ").append(ticket.getTickettitle()).append("\n");
        content.append("Please take necessary actions as soon as possible.\n\n");
        content.append("Thank you,\nYour Company");

        return content.toString();
    }
}
