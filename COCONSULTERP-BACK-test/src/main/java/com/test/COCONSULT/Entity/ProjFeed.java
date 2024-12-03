package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjFeed implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPjtFeed;


    private String content;



    private LocalDate timeUpd;

    private LocalDate fctsUpd;

    private String Description;

    @JsonIgnore
    @ManyToMany
    private Set<Projets> projets;


}
