package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.DTO.InvoiceStatus;
import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Invoice;
import com.test.COCONSULT.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    //List<Invoice> findByContract(Contract contract);

    List<Invoice> findByDueDateBeforeAndStatus(LocalDate currentDate, InvoiceStatus status);
}

