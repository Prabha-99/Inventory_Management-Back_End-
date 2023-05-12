package com.example.monara_backend.controller;

import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepo userRepo;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Hey Man..Finally Hahh");
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(){
        return ResponseEntity.ok("Are you happy now..???");
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
}
