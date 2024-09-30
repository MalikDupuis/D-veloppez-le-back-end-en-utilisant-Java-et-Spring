package com.example.demo.dto;

import com.example.demo.model.Rental;
import lombok.Data;

import java.util.List;

@Data
public class RentalResponse {
    private List<Rental> rentals;

    public RentalResponse(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public List<Rental> getRentals() {
        return rentals;
    }


}
