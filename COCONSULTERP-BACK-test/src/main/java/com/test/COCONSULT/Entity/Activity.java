package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.COCONSULT.DTO.ActivityType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Activity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idActivity;
    private Integer nbreOfTask;
    private String activityContent;
    @Enumerated(EnumType.STRING)
    private ActivityType taskType;
    @ManyToOne
    private Projets projet;
    @ManyToMany(mappedBy = "activitys")
    @JsonIgnore
    private Set<Tickets> tickets;
}
