package com.dadaev.tea_social.Service;

import com.dadaev.tea_social.exceptions.DirectoryInitializationException;
import com.dadaev.tea_social.exceptions.PathTraversalException;
import com.dadaev.tea_social.exceptions.SavingFileException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {
    @Value("${app.upload.dir}")
    private String uploadDir;
    @Value("${app.upload.base-url}")
    private String baseUrl;
    private Path rootLocation;

    @PostConstruct
    public void init() {
        try {
            this.rootLocation = Paths.get(uploadDir);
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new DirectoryInitializationException("failed to initialize directory for file storage");
        }
    }

    public String saveImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || originalFilename.isBlank()) {
                originalFilename = "image.jpg";
            }

            String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;

            Path destinationFile = this.rootLocation.resolve(Paths.get(uniqueFilename))
                    .normalize()
                    .toAbsolutePath();

            Path absoluteRootLocation = this.rootLocation.toAbsolutePath().normalize();

            if (!destinationFile.startsWith(absoluteRootLocation)) {
                throw new PathTraversalException("Attempt to save file outside the target folder");
            }

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            return baseUrl + uniqueFilename;

        } catch (IOException e) {
            throw new SavingFileException("failed to save file on disk");
        }
    }
}
