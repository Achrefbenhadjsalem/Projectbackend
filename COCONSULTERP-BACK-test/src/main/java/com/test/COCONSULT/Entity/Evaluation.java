package com.test.COCONSULT.Entity;

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
public class Evaluation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idEval;
    String type;
    float moy;
    String feedBack;

    boolean hasPrime;

    @ManyToOne
    User user;
}
