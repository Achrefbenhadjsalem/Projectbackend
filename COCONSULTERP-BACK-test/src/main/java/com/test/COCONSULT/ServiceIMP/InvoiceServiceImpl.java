package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.EtapeContract;
import com.test.COCONSULT.DTO.InvoiceStatus;
import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Invoice;
import com.test.COCONSULT.Entity.Payment;
import com.test.COCONSULT.Interfaces.InvoiceService;
import com.test.COCONSULT.Reposotories.ContractRepository;
import com.test.COCONSULT.Reposotories.InvoiceRepository;
import com.test.COCONSULT.Reposotories.PaymentRepository;
import com.test.COCONSULT.Services.ExportImportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
/*
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ContractRepository contractRepository ;


    private ExportImportService exportImportService ; // Inject the ExportImportService
    @Override
    public List<Invoice> retrieveInvoice() {
        return invoiceRepository.findAll() ;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice retrieveInvoice(Long id) {
        Optional<Invoice> InvoieceOptional = invoiceRepository.findById(id);
        return InvoieceOptional.orElse(null);
    }

    @Override
    public void removeInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void generateInvoicesForContract(Contract contract) {
        int numberOfInstallments = contract.getNbreTranche();
        double installmentAmount = contract.getMontant() / numberOfInstallments;
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < numberOfInstallments; i++) {
            LocalDate dueDate = currentDate.plusMonths(i + 1); // Assuming monthly installments
            Invoice invoice = Invoice.builder()
                    .invoiceNumber("INV-" + (i + 1))
                    .invoiceDate(currentDate)
                    .dueDate(dueDate)
                    .totalAmount(installmentAmount)
                    .status(InvoiceStatus.DRAFT) // Initial status
                    .contract(contract)
                    .build();
            // Save the invoice
            invoiceRepository.save(invoice);
        }
    }


    @Override
    public double calculateInvoiceTotal(Invoice invoice) {
        return invoice.getTotalAmount();
    }

    @Override
    public void recordPayment(Invoice invoice, Payment payment) {
        double remainingBalance = invoice.getTotalAmount() - payment.getAmount();
        invoice.setTotalAmount(remainingBalance);

        // Update invoice status based on remaining balance
        if (remainingBalance <= 0) {
            invoice.setStatus(InvoiceStatus.PAID);
        } else {
            invoice.setStatus(InvoiceStatus.PARTIAL);
        }

        payment.setContract(invoice.getContract());
        invoice.getContract().getPayments().add(payment);
    }

    @Override
    public void updateInvoiceStatus(Invoice invoice) {
        // Check if the invoice is partially paid
        if (invoice.getStatus() == InvoiceStatus.PARTIAL) {
            // Check if the remaining balance is zero
            if (invoice.getTotalAmount() <= 0) {
                // Update the status to PAID
                invoice.setStatus(InvoiceStatus.PAID);
            }
        }
        // You can add more specific business rules here, such as updating status based on due date
    }


    @Override
    public void updateContractStatus(Contract contract) {
        boolean allInvoicesPaid = contract.getInvoices().stream()
                .allMatch(invoice -> invoice.getStatus() == InvoiceStatus.PAID);

        if (allInvoicesPaid) {
            contract.setEtape(EtapeContract.TERMINE); // Update contract status
        }
    }


    @Override
    public List<Invoice> getInvoicesByContract(Long contractId) {
        Optional<Contract> optionalContract = contractRepository.findById(contractId);
        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            return invoiceRepository.findByContract(contract);
        } else {
            // Handle case when contract with given ID is not found
            return Collections.emptyList();
        }
    }


    @Override
    public List<Invoice> getOverdueInvoices() {
        LocalDate currentDate = LocalDate.now();
        return invoiceRepository.findByDueDateBeforeAndStatus(currentDate, InvoiceStatus.DRAFT);
    }

    // Export invoices and payments to CSV
    @Override
    public void exportDataToCSV(String fileName) {
        List<Invoice> invoices = retrieveInvoice();
        List<Payment> payments = paymentRepository.findAll(); // Assuming you have a method to retrieve all payments
        exportImportService.exportDataToCSV(invoices, payments, fileName);
    }

    // Import invoices and payments from CSV
    @Override
    public void importDataFromCSV(String fileName) {
        exportImportService.importDataFromCSV(fileName);
    }
*/
}
