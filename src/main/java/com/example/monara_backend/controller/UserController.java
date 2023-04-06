package com.example.monara_backend.controller;

import com.example.monara_backend.service.UserService;

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
}
