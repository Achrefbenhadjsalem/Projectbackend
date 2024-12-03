package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.ContractVerificationResult;
import com.test.COCONSULT.Entity.Payment;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PaymentService {
    List<Payment> retrievePayment();
    Payment updatePayment(Payment payment);
    Payment addPayment(Payment payment);
    Payment retrievePayment(Long idPayment);

    void removePayment(Long idPayment);
    void savePaymentsToDatabase(MultipartFile file) ;


    List<ContractVerificationResult> verifyPayments(List<Contract> contracts, List<Payment> payments) ;

    Map<LocalDate, Double> calculateDailySales(List<Payment> payments);



}

