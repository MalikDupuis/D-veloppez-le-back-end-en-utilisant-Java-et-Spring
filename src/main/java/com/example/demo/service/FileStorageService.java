package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/"; // Répertoire où les images seront stockées

    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        // Crée le répertoire de destination s'il n'existe pas
        Files.createDirectories(Paths.get(UPLOAD_DIR));

        // Génère un nom unique pour l'image (tu peux utiliser UUID ou autre)
        String fileName = System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        // Sauvegarde du fichier
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.write(filePath, file.getBytes());

        // Retourne l'URL du fichier (supposons que le serveur sert les fichiers depuis /uploads)
        return "http://localhost:8080/uploads/" + fileName;
    }
}
