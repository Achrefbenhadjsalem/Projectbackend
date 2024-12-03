package com.test.COCONSULT.Entity;

import com.test.COCONSULT.DTO.TypePrime;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Prime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idPrime;
    @Enumerated(EnumType.STRING)
    TypePrime type;
    double montant;

    @OneToOne
    Evaluation evaluation;
   @OneToOne
    User user;
}
