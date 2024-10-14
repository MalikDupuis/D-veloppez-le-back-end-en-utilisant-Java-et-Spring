package com.example.demo.service;

import com.example.demo.model.Rental;
import com.example.demo.repository.RentalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public void saveRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public Rental getRentalById(long id) {
        return rentalRepository.findById(id);
    }
}
