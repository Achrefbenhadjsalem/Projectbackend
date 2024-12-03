package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Invoice;
import com.test.COCONSULT.Entity.Payment;
import com.test.COCONSULT.Interfaces.RevenueService;
import com.test.COCONSULT.Reposotories.InvoiceRepository;
import com.test.COCONSULT.Reposotories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RevenueServiceImpl implements RevenueService {
   /* @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PaymentRepository paymentRepository;



    @Override
    public Map<String, Double> getTotalRevenueByMonth() {
        Map<String, Double> revenueByMonth = new HashMap<>();

        // Retrieve invoices and payments from the database
        List<Invoice> invoices = invoiceRepository.findAll();
        List<Payment> payments = paymentRepository.findAll();

        // Process invoices
        for (Invoice invoice : invoices) {
            String month = invoice.getInvoiceDate().getMonth().toString();
            double amount = invoice.getTotalAmount();

            revenueByMonth.put(month, revenueByMonth.getOrDefault(month, 0.0) + amount);
        }

        // Process payments
        for (Payment payment : payments) {
            String month = payment.getPaymentDate().getMonth().toString();
            double amount = payment.getAmount();

            revenueByMonth.put(month, revenueByMonth.getOrDefault(month, 0.0) + amount);
        }

        return revenueByMonth;
    }

    @Override
    public Map<Integer, Double> getTotalRevenueByYear() {
        Map<Integer, Double> revenueByYear = new HashMap<>();

        // Retrieve invoices and payments from the database
        List<Invoice> invoices = invoiceRepository.findAll();
        List<Payment> payments = paymentRepository.findAll();

        // Process invoices
        for (Invoice invoice : invoices) {
            int year = invoice.getInvoiceDate().getYear();
            double amount = invoice.getTotalAmount();

            revenueByYear.put(year, revenueByYear.getOrDefault(year, 0.0) + amount);
        }

        // Process payments
        for (Payment payment : payments) {
            int year = payment.getPaymentDate().getYear();
            double amount = payment.getAmount();

            revenueByYear.put(year, revenueByYear.getOrDefault(year, 0.0) + amount);
        }

        return revenueByYear;
    }*/
}
