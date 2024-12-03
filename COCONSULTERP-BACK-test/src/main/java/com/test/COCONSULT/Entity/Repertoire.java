package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.COCONSULT.DTO.Priorite;
import com.test.COCONSULT.DTO.TypeContact;
import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
//@JsonIgnoreProperties(ignoreUnknown = true)

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Repertoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRepertoire ;

    private String Contact ;

    @Enumerated(EnumType.STRING)
    private TypeContact typeContact ;

    @Enumerated(EnumType.STRING)
    private Priorite priorite ;

    private String NumTel ;

    private String email ;

    @ManyToMany(mappedBy = "repertoires")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "Repertoireee", cascade = CascadeType.ALL)
    private List<ActivitySalesTeam> Activities ;

    /*@Override
    public String toString() {
        return "Repertoire{" +
                "idRepertoire=" + idRepertoire +
                ", Contact='" + Contact + '\'' +
                ", typeContact=" + typeContact +
                ", priorite=" + priorite +
                ", NumTel='" + NumTel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }*/


}
