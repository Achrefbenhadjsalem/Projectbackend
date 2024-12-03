package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.ContractVerificationResult;
import com.test.COCONSULT.Entity.Payment;
import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Interfaces.PaymentService;
import com.test.COCONSULT.Reposotories.ContractRepository;
import com.test.COCONSULT.Reposotories.PaymentRepository;
import com.test.COCONSULT.Services.ExcelPaymentUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository ;



    @Override
    public List<Payment> retrievePayment() {
        return paymentRepository.findAll() ;
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment retrievePayment(Long idPayment) {
        Optional<Payment> PaymentOptional = paymentRepository.findById(idPayment);
        return PaymentOptional.orElse(null);
    }

    @Override
    public void removePayment(Long idPayment) {
        paymentRepository.deleteById(idPayment);
    }



    @Override
    public void savePaymentsToDatabase(MultipartFile file) {
        if (ExcelPaymentUploadService.isValidExcelFile(file)) {
            try {
                List<Payment> payments = ExcelPaymentUploadService.getPaymentsDataFromExcel(file.getInputStream());
                paymentRepository.saveAll(payments);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }


    @Override
    public List<ContractVerificationResult> verifyPayments(List<Contract> contracts, List<Payment> payments) {
        List<ContractVerificationResult> verificationResults = new ArrayList<>();

        for (Contract contract : contracts) {
            double installmentAmount = contract.getMontant() / contract.getNbreTranche();
            List<Boolean> installmentPaid = new ArrayList<>(contract.getNbreTranche());

            for (int i = 0; i < contract.getNbreTranche(); i++) {
                installmentPaid.add(false); // Initialize all installments as unpaid
            }

            for (Payment payment : payments) {
                if (contract.getReferenceContract().equals(payment.getReferenceNumber())) {
                    if (payment.getAmount() >= installmentAmount) {
                        for (int i = 0; i < contract.getNbreTranche(); i++) {
                            LocalDate startDate = contract.getDateContract().plusMonths(i);
                            LocalDate endDate = contract.getDateContract().plusMonths(i + 1);

                            if (payment.getPaymentDate().isAfter(startDate) && payment.getPaymentDate().isBefore(endDate)) {
                                // Payment is within the installment period
                                // Mark the installment as paid
                                installmentPaid.set(i, true);
                                break; // Move to the next payment
                            }
                        }
                    }
                }
            }

            // Create ContractVerificationResult object and add it to the list
            ContractVerificationResult result = new ContractVerificationResult(contract.getRepertoireContact(), installmentPaid);
            verificationResults.add(result);
        }

        return verificationResults;
    }


    @Override
    public Map<LocalDate, Double> calculateDailySales(List<Payment> payments) {
        // Create a map to store the daily sales
        Map<LocalDate, Double> dailySales = new HashMap<>();

        // Iterate through the list of payments
        for (Payment payment : payments) {
            // Get the payment date
            LocalDate paymentDate = payment.getPaymentDate();

            // Get the amount of the payment
            double amount = payment.getAmount();

            // Update the daily sales map
            dailySales.put(paymentDate, dailySales.getOrDefault(paymentDate, 0.0) + amount);
        }

        return dailySales;
    }




}








