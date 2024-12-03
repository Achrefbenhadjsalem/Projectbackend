package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_test;

    @OneToMany( mappedBy = "test",cascade = CascadeType.ALL)
    private List<Qestion> questions = new ArrayList<>();
 @ManyToMany( mappedBy = "test",cascade = CascadeType.ALL)
 List<Quiz> quiz = new ArrayList<>();
    @ManyToOne(  cascade = CascadeType.ALL)
    @JsonIgnore
    private Candidat candidat;

    double finalmark;



}


