package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_quiz;
    private String titre;
    private String numberOfQuestions;



    @ToString.Exclude
@JsonIgnore
    @ManyToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
    List<Candidat> candidat = new ArrayList<>();
    @ToString.Exclude

    @OneToMany(mappedBy = "quiz" , cascade = CascadeType.ALL, orphanRemoval = true)
    List<Qestion> questionList = new ArrayList<>() ;
    @ManyToMany ( )
    @JsonIgnore
    List<test> test = new ArrayList<>() ;
    @ToString.Exclude
    @JsonIgnore
 @ManyToOne

    JobOpport jobOpport;


}
