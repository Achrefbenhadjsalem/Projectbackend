package com.test.COCONSULT.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.Iterator;
import java.util.List;
import javax.persistence.*;
import java.util.ArrayList;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id_condidat;
    private String email;
    private String pdfFile;
    // profile
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String photo;
    private String competence;
private String info;

    @JsonIgnore
    @ManyToMany
    List<Quiz> quiz = new ArrayList<>();
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
   JobOpport jobOpport;
    @JsonIgnore
    @OneToMany(mappedBy="candidat",cascade = CascadeType.ALL )
    List<test>  test= new ArrayList<>();
    @OneToMany(mappedBy="candidat",cascade = CascadeType.ALL)

    List<Entretien>  entretien= new ArrayList<>();
    @OneToMany(mappedBy = "candidat"  )
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    List<Reclamation> reclamation= new ArrayList<>();



    public boolean aPasseTest() {
        // Parcourir la liste des tests associés à ce candidat
        for (test test : test) {

            if (test.getFinalmark() >= 0) {
                return true; // Le candidat a passé au moins un test
            }
        }
        return false; // Le candidat n'a pas passé de test
    }
}
