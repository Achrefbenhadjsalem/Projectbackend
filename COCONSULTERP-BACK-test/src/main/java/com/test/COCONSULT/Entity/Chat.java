package com.test.COCONSULT.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.COCONSULT.DTO.MessageType;
import io.swagger.v3.core.converter.AnnotatedType;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Date date=new Date();
    private String message;
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "sender_id")
   // @JsonIgnore
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "groupChat_id")
  //  @JsonIgnore
    private GroupChat groupChat;


}
