package com.test.COCONSULT.Services;

import com.test.COCONSULT.Interfaces.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LocalFileStorageService implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String storeFile(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        try {
            Path filePath = Paths.get(uploadDir + File.separator + fileName);
            Files.copy(file.getInputStream(), filePath);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage());
        }
    }

    public List<String> getMatchingImagePaths(List<String> databaseImageNames) {
        List<String> matchingImagePaths = new ArrayList<>();
        File directory = new File(uploadDir);

        // Check if the directory exists
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String fullFileName = file.getName();
                        String[] parts = fullFileName.split("_"); // Split the filename based on underscore
                        //if (parts.length == 2) {
                        String actualFileName = "" ;
                        for ( int i = 1; i < parts.length; i++) {
                            actualFileName += parts[i] ; // Extract the actual filename
                            actualFileName += '_';
                        }

                        // Check if the file name ends with an underscore
                        if (actualFileName.endsWith("_")) {
                            // Remove the last character (underscore)
                            actualFileName = actualFileName.substring(0, actualFileName.length() - 1);

                        }


                        if (databaseImageNames.get(0).contains(actualFileName.toString())) {
                            matchingImagePaths.add(file.getAbsolutePath());
                            break;
                        }
                    }
                }
            }
        }
        return matchingImagePaths;
    }


}