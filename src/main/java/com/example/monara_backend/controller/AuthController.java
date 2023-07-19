package com.example.monara_backend.controller;

import com.example.monara_backend.Configuration.AuthenticationRequest;
import com.example.monara_backend.Configuration.AuthenticationResponse;
import com.example.monara_backend.Configuration.RegisterRequest;
import com.example.monara_backend.dto.navBarLogin;
import com.example.monara_backend.model.User;
import com.example.monara_backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")

    public ResponseEntity<AuthenticationResponse>login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/CurrentUser")
    public navBarLogin getCurrentUser(){  //Getting the CurrentUser username Using Authentication Interface that comes with Spring Security
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String firstname=authentication.getName();
        return new navBarLogin(firstname);
    }
    @GetMapping("/UserProfile")
    public User getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return authenticationService.getUserByEmail(email);
    }

    @PutMapping("UpdateProfile")
    public ResponseEntity<User> updateUserProfile (@RequestBody User user){
        return new ResponseEntity<>(authenticationService.updateUserProfile(user), HttpStatus.OK);
    }

}
