package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTeam;
    private Integer capacity;
    private String teamLeader;
    private String teamName;
    @JsonIgnore
    @OneToMany
    private Set<User> users;





}