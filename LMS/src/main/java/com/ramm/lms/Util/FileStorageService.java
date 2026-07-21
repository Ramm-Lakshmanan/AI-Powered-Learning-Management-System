package com.ramm.lms.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service

public class FileStorageService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveFile(MultipartFile file) {

        try {

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFileName = file.getOriginalFilename();

            String uniqueFileName =
                    UUID.randomUUID() + "_" + originalFileName;

            Path filePath = uploadPath.resolve(uniqueFileName);

            Files.copy(file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);

            return uniqueFileName;

        } catch (IOException e) {

            throw new RuntimeException("Failed to store file.");
        }
    }

    public byte[] getFile(String fileName) {

        try {

            Path path = Paths.get(uploadDir)
                    .resolve(fileName);

            return Files.readAllBytes(path);

        } catch (IOException e) {

            throw new RuntimeException("File not found.");
        }
    }

    public void deleteFile(String fileName) {

        try {

            Path path = Paths.get(uploadDir)
                    .resolve(fileName);

            Files.deleteIfExists(path);

        } catch (IOException e) {

            throw new RuntimeException("Unable to delete file.");
        }
    }
}
