package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Interfaces.FileStorageService;

import com.test.COCONSULT.Reposotories.ContractRepository;
import com.test.COCONSULT.Services.LocalFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PDFController {
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    ContractRepository contractRepository ;
    @Autowired
    LocalFileStorageService localFileStorageService ;

    @Autowired
    public PDFController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/pdfs/{pdfName:.+}")
    public ResponseEntity<byte[]> getPdf(@PathVariable String pdfName) throws IOException {
        List<String> pdfs = new ArrayList<>() ;

        pdfs.add(pdfName) ;
        List<String> Finalpdfs = localFileStorageService.getMatchingImagePaths(pdfs) ;
        // Construct the full path to the PDF file
        Path pdfPath = Paths.get(Finalpdfs.get(0));

        // Read the PDF bytes from the file
        byte[] pdfBytes = Files.readAllBytes(pdfPath);

        // Determine the MIME type of the PDF based on its file extension
        String contentType = Files.probeContentType(pdfPath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(contentType));

        // Return the PDF bytes along with appropriate headers
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public String uploadFile(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }

        try {
            // Store the file and get the file name
            String fileName = fileStorageService.storeFile(file);
            Contract contract = new Contract();
            // Set email provided by the user
            contract.setDescription(fileName);
            // Set other properties of Candidat as needed

            //contractRepository.save(contract);
            return "File uploaded successfully: " + fileName;
        } catch (Exception e) {
            return "Failed to upload file: " + e.getMessage();
        }

    }

}