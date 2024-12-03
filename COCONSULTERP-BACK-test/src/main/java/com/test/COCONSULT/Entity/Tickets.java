package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.COCONSULT.DTO.TicketPriority;
import com.test.COCONSULT.DTO.TicketStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Tickets implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTicket;
    @Enumerated(EnumType.STRING)
    private TicketPriority ticketPriority;
    private String TicketContent;
    private String tickettitle;
    private LocalDate dateAssigned;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
    @ManyToMany
    @JsonIgnore
    private Set<Activity> activitys;
    @ManyToOne
    private User user;


}
