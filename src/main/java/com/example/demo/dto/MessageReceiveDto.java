package com.example.demo.dto;

import lombok.Data;

@Data
public class MessageReceiveDto {
    private long rental_id;
    private long user_id;
    private String message;
}
