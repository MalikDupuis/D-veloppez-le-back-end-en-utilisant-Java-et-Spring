package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.MessageReceiveDto;
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
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageReceiveDto messageReceiveDto) {

        System.out.println(messageReceiveDto.getMessage());
        System.out.println(messageReceiveDto.getUser_id());
        Message message = new Message();
        message.setMessage(messageReceiveDto.getMessage());
        message.setUser_id(messageReceiveDto.getUser_id());
        message.setRental_id(messageReceiveDto.getRental_id());
        messageService.saveMessage(message);
        MessageDto messageDto = new MessageDto("Message send with success");
        return ResponseEntity.ok(messageDto);
    }
}
