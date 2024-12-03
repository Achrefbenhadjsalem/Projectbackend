package com.test.COCONSULT.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identretien;
    private String date;
    private String heure ;
    private String lien ;
    @ManyToOne
    Candidat candidat;


}
