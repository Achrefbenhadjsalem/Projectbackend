package com.test.COCONSULT.Entity;

import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DemandeConge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startDate;
    String type;
    @ManyToOne
    User user;
    @Lob
    byte[] certificate; // File upload field
    String certificateFileName;
    String certificateContentType;
}

