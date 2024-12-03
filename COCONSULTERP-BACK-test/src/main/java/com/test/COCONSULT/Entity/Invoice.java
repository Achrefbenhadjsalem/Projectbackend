package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.COCONSULT.DTO.InvoiceStatus;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String invoiceNumber;
        private LocalDate invoiceDate;
        private LocalDate dueDate;
        private double totalAmount;

        @Enumerated(EnumType.STRING)
        private InvoiceStatus status;

      /*  @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "contract_id")
        @JsonIgnore
        private Contract contract;*/

        // Other attributes and methods
    }




