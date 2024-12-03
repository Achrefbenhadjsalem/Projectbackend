package com.test.COCONSULT.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RappelPointage {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int id;
    Date dateRappel;
    String verificationCode;
    @OneToOne
    User user;

}
