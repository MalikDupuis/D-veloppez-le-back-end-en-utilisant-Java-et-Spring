package com.example.demo.dto;

public class RentalMessageResponse {
    private String message;

    public RentalMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}