package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Salaire  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idSal;

    double salaire;
    double impot ;
    String currency ;
    Date date ;
    @ManyToMany
    Set<UserActivity> setUserActivity;


    @ManyToOne
    User user;


}
