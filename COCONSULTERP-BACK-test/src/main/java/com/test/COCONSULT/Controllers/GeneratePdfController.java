package com.test.COCONSULT.Controllers;

import com.itextpdf.text.DocumentException;
import com.test.COCONSULT.DTO.EtapeContract;
import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Interfaces.IpdfContarct;
import com.test.COCONSULT.Services.PDFGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class GeneratePdfController {

    private final PDFGenerationService pdfGenerationService;

    @Autowired
    public GeneratePdfController(PDFGenerationService pdfGenerationService) {
        this.pdfGenerationService = pdfGenerationService;
    }
    @GetMapping("/pdf")
    public byte[] generatePdf(@PathVariable Long idContract , @RequestParam LocalDate DateContract , @RequestParam String RepertoireContact, @RequestParam String Etape, @RequestParam float Montant, @RequestParam int NbreTranche) throws IOException {
        // Retrieve contract and house based on their IDs
        Contract contract = new Contract();
        // RequirementCollocation requirementCollocation = new RequirementCollocation();
        contract.setDateContract(DateContract);
        contract.setEtape(EtapeContract.valueOf(Etape));
        contract.setRepertoireContact(RepertoireContact);
        contract.setMontant(Montant);
        contract.setNbreTranche(NbreTranche);
        //contract = contractService.addContractAffectRep(contract, repertoireId);
        //House house = houseService.findHouseById(houseId);

        // Generate the PDF using the IpdfHouse service
        return IpdfContarct.generatePdf(idContract);
    }
    /*

    @GetMapping("/generatePdf")
    public ResponseEntity<byte[]> generatePdf() {
        try {
            byte[] pdfData = pdfGenerationService.generatePDF();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "generated_pdf.pdf");

            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
