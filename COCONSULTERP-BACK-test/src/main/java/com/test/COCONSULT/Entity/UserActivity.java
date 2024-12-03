package com.test.COCONSULT.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserActivity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idAct;

    int nbrDayO0ff;
    int hrSupp;
    String absJustf;
    @ManyToMany (mappedBy = "setUserActivity")

    Set<Salaire> setSalaires;
}
