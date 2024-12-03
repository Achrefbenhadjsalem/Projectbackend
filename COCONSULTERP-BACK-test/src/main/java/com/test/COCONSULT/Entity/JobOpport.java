package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOpport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_offre;
    private String titre;
    private String description;
    private String qualifications;
    private String lieu;
    private Date dateLimite;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "jobOpport")
    List<Candidat> candidat = new ArrayList<>();
    @ToString.Exclude
    @JsonIgnore
@OneToMany(mappedBy = "jobOpport")
List<Quiz> quiz = new ArrayList<>();





}
