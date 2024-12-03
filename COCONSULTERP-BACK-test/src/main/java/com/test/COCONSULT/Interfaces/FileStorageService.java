package com.test.COCONSULT.Interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file);

}
