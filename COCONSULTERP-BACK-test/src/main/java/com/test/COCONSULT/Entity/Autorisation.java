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
public class Autorisation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idAuto;
    boolean Autorisation;

    @OneToOne
    Pointage pointage;
    @ManyToOne
    User user;
}
