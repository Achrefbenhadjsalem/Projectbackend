package com.test.COCONSULT.Services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.test.COCONSULT.DTO.InvoiceStatus;
import com.test.COCONSULT.DTO.TypePayment;
import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Invoice;
import com.test.COCONSULT.Entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExportImportService {

    @PersistenceContext
    private EntityManager entityManager;

    // Export Invoices and Payments to CSV
    public void exportDataToCSV(List<Invoice> invoices, List<Payment> payments, String fileName) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            // Write headers for invoices
            String[] invoiceHeaders = {"Invoice Number", "Invoice Date", "Due Date", "Total Amount", "Status"};
            writer.writeNext(invoiceHeaders);

            // Write invoice data
            for (Invoice invoice : invoices) {
                String[] invoiceData = {invoice.getInvoiceNumber(), invoice.getInvoiceDate().toString(), invoice.getDueDate().toString(), String.valueOf(invoice.getTotalAmount()), invoice.getStatus().toString()};
                writer.writeNext(invoiceData);
            }

            // Write headers for payments
            String[] paymentHeaders = {"Payment Date", "Amount", "Payment Method", "Reference Number"};
            writer.writeNext(paymentHeaders);

            // Write payment data
            for (Payment payment : payments) {
                String[] paymentData = {payment.getPaymentDate().toString(), String.valueOf(payment.getAmount()), payment.getPaymentMethod().toString(), payment.getReferenceNumber()};
                writer.writeNext(paymentData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Import Invoices and Payments from CSV
    public void importDataFromCSV(String fileName) {
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] line;
            boolean isFirstLine = true;
            while ((line = reader.readNext()) != null) {
                if (isFirstLine) {
                    // Skip headers
                    isFirstLine = false;
                    continue;
                }
                if (line.length == 5) {
                    // Invoice data
                    Invoice invoice = new Invoice();
                    invoice.setInvoiceNumber(line[0]);
                    invoice.setInvoiceDate(LocalDate.parse(line[1]));
                    invoice.setDueDate(LocalDate.parse(line[2]));
                    invoice.setTotalAmount(Double.parseDouble(line[3]));
                    invoice.setStatus(InvoiceStatus.valueOf(line[4]));
                    // Save invoice to database
                    entityManager.persist(invoice);
                } else if (line.length == 4) {
                    // Payment data
                    Payment payment = new Payment();
                    payment.setPaymentDate(LocalDate.parse(line[0]));
                    payment.setAmount(Double.parseDouble(line[1]));
                    payment.setPaymentMethod(TypePayment.valueOf(line[2]));
                    payment.setReferenceNumber(line[3]);
                    // Save payment to database
                    entityManager.persist(payment);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
