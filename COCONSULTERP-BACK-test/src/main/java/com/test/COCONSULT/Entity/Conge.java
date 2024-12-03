package com.test.COCONSULT.Entity;

import com.test.COCONSULT.DTO.TypeConge;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Conge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idCong;
    int duree;
    Date dateDebut;

    Date dateFin;
    @Enumerated(EnumType.STRING)
    TypeConge typeConge;

    @ManyToOne
    User user;


}
