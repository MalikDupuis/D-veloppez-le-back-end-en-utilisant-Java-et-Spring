package com.example.demo.controller;

import com.example.demo.model.Rental;
import com.example.demo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @GetMapping()
    public List<Rental> rentals() {
        return rentalService.getAllRentals();
    }

    @PostMapping()
    public ResponseEntity<?> rental(@RequestBody Rental rental) {
        rentalService.saveRental(rental);
        return ResponseEntity.ok("Rental created !");
    }
}
