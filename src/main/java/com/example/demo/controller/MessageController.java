package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.service.JWTService;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    private JWTService jwtService;

    public MessageController(JWTService jwtService) {
        this.jwtService = jwtService;
    }


    @PostMapping
    public ResponseEntity<MessageDto> createMessage(@ModelAttribute MessageDto messageDto, @RequestHeader("Authorization") String authorizationHeader) {

        Message message = new Message();
        message.setMessage(messageDto.getMessage());
        messageService.saveMessage(message);
        messageDto.setMessage("Message saved successfully");
        return ResponseEntity.ok(messageDto);
    }
}
