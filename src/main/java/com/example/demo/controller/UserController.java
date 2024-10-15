package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserDto user(@PathVariable Long userId) {
        User u = userService.findById(userId);
        UserDto userDto = new UserDto();
        userDto.setId(u.getId());
        userDto.setName(u.getName());
        userDto.setEmail(u.getEmail());
        userDto.setCreated_at(u.getCreated_at());
        userDto.setUpdated_at(u.getUpdated_at());
        return userDto;
    }
}
