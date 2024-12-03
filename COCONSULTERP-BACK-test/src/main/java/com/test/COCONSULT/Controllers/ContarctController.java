package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.DTO.EtapeContract;
import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Interfaces.ContractService;
import com.test.COCONSULT.Interfaces.IpdfContarct;
import com.test.COCONSULT.Services.PDFGenerationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("Contract")
@RestController
@AllArgsConstructor
public class ContarctController{
    private final ContractService contractService ;
    private final PDFGenerationService pdfGenerationService;



    @GetMapping("/GetAllContract")
    public ResponseEntity<List<Contract>> retrieveAllContracts() {
        List<Contract> contractList = contractService.retrieveContracts();
        return ResponseEntity.ok(contractList);
    }


    @GetMapping("/GetContractByID/{id}")
    public ResponseEntity<Contract> retrieveContract(@PathVariable("id") Long idContract ) {
        Contract contract =  contractService.retrieveContract(idContract);
        if (contract != null) {
            return ResponseEntity.ok(contract);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/ajouterContract")
    public Contract addContrat(@RequestBody Contract contract ) {
        return contractService.addContract(contract);

    }

    @PostMapping("/ajouterContract/{repertoireId}")
    public ResponseEntity<Contract> addContract(@RequestBody Contract contract, @PathVariable Long repertoireId) {
        Contract addedContract = contractService.addContractAffectRep(contract, repertoireId);
        if (addedContract != null) {
            return ResponseEntity.ok(addedContract);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateContractAffectRepo/{contractId}/repertoire/{repertoireId}")
    public ResponseEntity<Contract> updateContract(@RequestBody Contract updatedContract,
                                                   @PathVariable Long contractId,
                                                   @PathVariable Long repertoireId) {
        Contract updated = contractService.updateContractAffectRep(updatedContract, contractId, repertoireId);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/updateContract/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable("id") Long idContract, @RequestBody Contract contract) {
        contract.setIdContract(idContract); // Set the ID from the path variable
        Contract updatedConract = contractService.updateContract(contract);
        if (updatedConract != null) {
            return ResponseEntity.ok(updatedConract);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{idContract}/repertoireContact")
    public ResponseEntity<String> getRepertoireContactByContractId(@PathVariable Long idContract) {
        String repertoireContact = contractService.getRepertoireContactByContractId(idContract);

        if (repertoireContact != null) {
            return ResponseEntity.ok(repertoireContact);
        } else {
            // Handle the case when repertoireContact is null (e.g., contract not found)
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/RemoveContract/{id}")
    public ResponseEntity<Void> removeContract(@PathVariable("id") Long idContract) {
        contractService.removeContract(idContract);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/allWithRepertoireContact")
    public ResponseEntity<List<Contract>> retrieveContractsWithRepertoireContact() {
        List<Contract> contracts = contractService.retrieveContractsWithRepertoireContact();
        return ResponseEntity.ok(contracts);
    }


    @PostMapping("/addContractAndGeneratePdf/{repertoireId}")
    public ResponseEntity<byte[]> addContractAndGeneratePdf(@RequestBody Contract contract, @PathVariable Long repertoireId) {
        Contract addedContract = contractService.addContractAffectRep(contract, repertoireId);
        if (addedContract != null) {
            try {
                byte[] pdfBytes = pdfGenerationService.generatePdf(addedContract.getIdContract());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                // Optionally, you can set the filename in the response headers
                headers.setContentDispositionFormData("filename", "contract.pdf");
                return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            } catch (IOException e) {
                // Handle PDF generation error
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    }




