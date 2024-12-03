package com.test.COCONSULT.Reposotories;


import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    //List<Payment> findByContract(Contract contract);
}
