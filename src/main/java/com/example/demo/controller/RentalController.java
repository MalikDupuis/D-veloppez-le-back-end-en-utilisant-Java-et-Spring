package com.example.demo.controller;

import com.example.demo.dto.RentalDto;
import com.example.demo.dto.RentalResponse;
import com.example.demo.model.Rental;
import com.example.demo.model.User;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.JWTService;
import com.example.demo.service.RentalService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserService userService;

    private JWTService jwtService;

    public RentalController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping()
    public RentalResponse rentals() {
        List<Rental> rentalList = rentalService.getAllRentals();
        return new RentalResponse(rentalList);
    }


    @GetMapping("/{rentalId}")
    public Rental rental(@PathVariable Long rentalId) {
        return rentalService.getRentalById(rentalId);
    }

    @PostMapping()
    public ResponseEntity<?> rental(@ModelAttribute  RentalDto rentalDto, @RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Sauvegarde de l'image
            String imageUrl = fileStorageService.uploadFile(rentalDto.getPicture());

            // Création de l'objet Rental
            Rental rental = new Rental();
            rental.setName(rentalDto.getName());
            rental.setPicture(imageUrl);
            rental.setDescription(rentalDto.getDescription());
            rental.setPrice(rentalDto.getPrice());
            rental.setOwnerId(rentalDto.getOwnerId());
            rental.setCreated_at(rentalDto.getCreated_at());
            rental.setUpdated_at(rentalDto.getUpdated_at());
            rental.setSurface(rentalDto.getSurface());
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7); // Retirer "Bearer " pour ne garder que le token

                // Valider le token
                if (jwtService.validateToken(token)) {
                    // Extraire l'email du token
                    String email = jwtService.extractEmailFromToken(token);

                    // Récupérer l'utilisateur depuis la base de données avec l'email
                    User user = userService.findByEmail(email);

                    rental.setOwnerId(user.getId());

                } else {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token JWT invalide");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token JWT manquant ou mal formé");
            }

            // Sauvegarde dans la base de données
            rentalService.saveRental(rental);

            return ResponseEntity.ok("savedRental");

        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
