package com.test.COCONSULT.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Date date=new Date();
    private String email;
    private String nom;
    private String message;
    private String tel;
    private boolean notificationSent; // Flag indicating whether notification has been sent
}

