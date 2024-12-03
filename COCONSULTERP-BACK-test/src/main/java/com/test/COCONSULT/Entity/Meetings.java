package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.COCONSULT.DTO.TypeMeet;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Meetings implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMeeting;
    private String titleMeet;
    private Date dateMeeting;
    @Enumerated(EnumType.STRING)
    private TypeMeet typeMeet;
    @ManyToOne
    @JsonIgnore
    private Projets projets;

    @JsonIgnore
    @OneToMany
    private Set<Team> teams;


}