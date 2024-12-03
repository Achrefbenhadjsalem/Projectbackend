package com.test.COCONSULT.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GroupChat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String groupTitle;
    private String rules;
    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "role_id")
  //  @JsonIgnore
    private Role role;


    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinTable(name = "group_chat_users",
            joinColumns = @JoinColumn(name = "group_chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
   // @JsonIgnore
    private Set<User> users = new HashSet<>();

}
