package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Invoice;
import com.test.COCONSULT.Entity.Payment;
import com.test.COCONSULT.Reposotories.ContractRepository;
import com.test.COCONSULT.ServiceIMP.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
/*
    @Autowired
    private InvoiceServiceImpl invoiceService;

    @Autowired
    private ContractRepository contractRepository ;

    @GetMapping("/GetAllInvoice")
    public ResponseEntity<List<Invoice>> retrieveInvoice() {
        List<Invoice> InvoiceList = invoiceService.retrieveInvoice();
        return ResponseEntity.ok(InvoiceList);
    }
    @PostMapping("/generate")
    public void generateInvoices(@RequestBody Contract contract) {
        invoiceService.generateInvoicesForContract(contract);
    }

    @GetMapping("/total/{id}")
    public double calculateInvoiceTotal(@PathVariable Long id) {
        Invoice invoice = invoiceService.retrieveInvoice(id);
        return invoiceService.calculateInvoiceTotal(invoice);
    }

    @PostMapping("/{invoiceId}/record-payment")
    public void recordPayment(@PathVariable Long invoiceId, @RequestBody Payment payment) {
        Invoice invoice = invoiceService.retrieveInvoice(invoiceId);
        invoiceService.recordPayment(invoice, payment);
    }

    @PutMapping("/{id}/update-status")
    public void updateInvoiceStatus(@PathVariable Long id) {
        Invoice invoice = invoiceService.retrieveInvoice(id);
        invoiceService.updateInvoiceStatus(invoice);
    }

    @PutMapping("/update-contract-status")
    public void updateContractStatus(@RequestBody Contract contract) {
        invoiceService.updateContractStatus(contract);
    }

    @GetMapping("/contract/{contractId}")
    public List<Invoice> getInvoicesByContract(@PathVariable Long contractId) {
        Optional<Contract> optionalContract = contractRepository.findById(contractId);
        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            return invoiceService.getInvoicesByContract(contract.getIdContract());
        } else {
            // Handle case when contract with given ID is not found
            return Collections.emptyList();
        }
    }


    @GetMapping("/overdue")
    public List<Invoice> getOverdueInvoices() {
        return invoiceService.getOverdueInvoices();
    }

    @PostMapping("/export-csv/{fileName}")
    public void exportDataToCSV(@PathVariable String fileName) {
        invoiceService.exportDataToCSV(fileName);
    }

    @PostMapping("/invoices/import-csv")
    public ResponseEntity<?> importCSV(@RequestParam("file") MultipartFile file) {
        invoiceService.importDataFromCSV(fileName);
    } */
}
