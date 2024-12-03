package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.COCONSULT.DTO.TypePayment;
import com.test.COCONSULT.Entity.Contract;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    private LocalDate paymentDate;
    private double amount;
    private TypePayment paymentMethod;
    private String referenceNumber;


}
