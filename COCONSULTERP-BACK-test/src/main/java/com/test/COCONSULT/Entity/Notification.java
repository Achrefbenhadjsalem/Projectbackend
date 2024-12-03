package com.test.COCONSULT.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
//@Builder

@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date=new Date();
    private String title;
    private String message;
    @OneToOne
    private AdminMsg adminMsgId; // Reference to the associated AdminMsg
    @ManyToMany

    private List<User> recipients; // Recipients of the notification (can be email addresses, user IDs, etc.)
}
