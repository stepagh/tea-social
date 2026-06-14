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
            // На всякий случай страхуемся от NullPointerException, если имени файла нет
            if (originalFilename == null || originalFilename.isBlank()) {
                originalFilename = "image.jpg";
            }

            String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;

            // 1. Формируем путь к файлу
            Path destinationFile = this.rootLocation.resolve(Paths.get(uniqueFilename))
                    .normalize()
                    .toAbsolutePath(); // Сразу переводим в абсолютный вид

            // 2. Получаем АБСОЛЮТНЫЙ путь к нашей корневой папке загрузок
            Path absoluteRootLocation = this.rootLocation.toAbsolutePath().normalize();

            // 3. Надежная проверка: целевой файл ДОЛЖЕН НАЧИНАТЬСЯ с пути нашей папки
            if (!destinationFile.startsWith(absoluteRootLocation)) {
                throw new PathTraversalException("Attempt to save file outside the target folder");
            }

            // 4. Копируем файл
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            return baseUrl + uniqueFilename;

        } catch (IOException e) {
            throw new SavingFileException("failed to save file on disk");
        }
    }
}
