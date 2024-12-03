package com.test.COCONSULT.Entity;


import com.test.COCONSULT.DTO.Priorite;
import com.test.COCONSULT.DTO.ProspectStatus;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prospect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProspect ;

    private String Name  ;
    private String Title ;
    private String Email ;
    private String NumTel ;

    @Enumerated(EnumType.STRING)
    private ProspectStatus Status ;



}