package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.COCONSULT.DTO.ClassSalesTeam;
import com.test.COCONSULT.DTO.Status;
import com.test.COCONSULT.DTO.TypeSalesActivity;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivitySalesTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActSale ;
    private LocalDate HeureStart ;
    private LocalDate HeureEnd ;
    private String Description ;
    @Enumerated(EnumType.STRING)
    private TypeSalesActivity TypeAct ;
    @Enumerated(EnumType.STRING)
    private Status status ;
    @Enumerated(EnumType.STRING)
    private ClassSalesTeam classSalesTeam ;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repertoire_id")
    private Repertoire Repertoireee;

    private String repertoireActivity;


    public String getRepertoireContact() {
        return repertoireActivity;
    }

    public void setRepertoireContact(String repertoireContact) {
        this.repertoireActivity = repertoireContact;
    }


}
