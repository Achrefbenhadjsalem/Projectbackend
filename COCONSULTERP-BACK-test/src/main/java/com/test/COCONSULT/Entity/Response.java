package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idchoice;
    private String content;
    private boolean reponse;
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    Qestion question;



}
