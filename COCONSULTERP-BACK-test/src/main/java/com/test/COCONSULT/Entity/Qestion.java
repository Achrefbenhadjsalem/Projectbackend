package com.test.COCONSULT.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Qestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQuest;
    private int ponderation;

    private String content;
    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String answer;
    private String selected_answer;
@ManyToOne
@JsonIgnore
test test;
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    Quiz quiz;







}


