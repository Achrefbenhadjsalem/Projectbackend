package com.test.COCONSULT.Entity;

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
//@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pointage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idPoint;

    Date dateEntr;


    @ManyToOne
    User user;
}
