package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.util.Map;

@Service
public class FileStorageService {

    private final Cloudinary cloudinary;

    // Constructor: Initialize Cloudinary with credentials from .env
    public FileStorageService() {
        Dotenv dotenv = Dotenv.load();
        this.cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        this.cloudinary.config.secure = true;
    }

    // Method to upload a file to Cloudinary
    public String uploadFile(MultipartFile file) throws IOException {
        // Convert the MultipartFile to a format Cloudinary can handle
        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", true,  // Set to true for unique filenames
                "overwrite", true
        );

        // Perform the upload and get the result map
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);

        // Return the URL of the uploaded image
        return uploadResult.get("url").toString();
    }
}
