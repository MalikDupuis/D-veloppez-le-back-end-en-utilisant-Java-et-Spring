package com.example.demo.controller;

import com.example.demo.dto.TokenResponse;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.model.User;
import com.example.demo.service.JWTService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    private JWTService jwtService;

    public AuthController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody UserLoginDto userLoginDto) {
        User user = userService.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\":\"Email ou mot de passe incorrect\"}");
        }
        String token = jwtService.generateToken(userLoginDto.getEmail());

        TokenResponse tokenResponse = new TokenResponse(token);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.save(user);
        String token = jwtService.generateToken(user.getEmail());
        // Créer une réponse JSON avec le token
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        // Retourner une réponse HTTP 200 avec le token en JSON
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public User me(@RequestHeader("Authorization") String authorizationHeader) {

        // Extraire le token du header Authorization
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Retirer "Bearer " pour ne garder que le token

            // Valider le token
            if (jwtService.validateToken(token)) {
                // Extraire l'email du token
                String email = jwtService.extractEmailFromToken(token);

                // Récupérer l'utilisateur depuis la base de données avec l'email
                User user = userService.findByEmail(email);


                return user;
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token JWT invalide");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token JWT manquant ou mal formé");
        }
    }
}
