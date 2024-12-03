package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.COCONSULT.DTO.EtapeContract;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
//@JsonIgnoreProperties(ignoreUnknown = true)

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContract ;
    private String Description ;
    private LocalDate DateContract ;
    private double Montant ;
    private int NbreTranche ;
    private String referenceContract;
    //private  Long repertoireId;
    @Enumerated(EnumType.STRING)
    private EtapeContract Etape ;

    @ElementCollection
    private List<Boolean> installmentPaid;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Repertoire> repertoires = new ArrayList<>(); // Initialize the list


     private String repertoireContact;
/*
    @Override
    public String toString() {
        return "Contract{" +
                "idContract=" + idContract +
                ", Description='" + Description + '\'' +
                ", DateContract=" + DateContract +
                ", Montant=" + Montant +
                ", NbreTranche=" + NbreTranche +
                ", referenceContract='" + referenceContract + '\'' +
                ", Etape=" + Etape +
                ", installmentPaid=" + installmentPaid +
                ", repertoireContact='" + repertoireContact + '\'' +
                '}';
    }*/

}