package com.example.monara_backend.controller;

import com.example.monara_backend.Configuration.AuthenticationRequest;
import com.example.monara_backend.Configuration.AuthenticationResponse;
import com.example.monara_backend.Configuration.RegisterRequest;
import com.example.monara_backend.dto.navBarLogin;
import com.example.monara_backend.service.AuthenticationService;
import com.example.monara_backend.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    private final PasswordResetService passwordResetService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")

    public ResponseEntity<AuthenticationResponse>login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        passwordResetService.initiatePasswordReset(email);
        return ResponseEntity.ok("Password reset instructions sent to your email.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String token, @RequestParam("password") String password) {
        passwordResetService.resetPassword(token, password);
        return ResponseEntity.ok("Password reset successfully.");
    }

    @GetMapping("/CurrentUser")
    public navBarLogin getCurrentUser(){  //Getting the CurrentUser username Using Authentication Interface that comes with Spring Security
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String firstname=authentication.getName();
        return new navBarLogin(firstname);
    }
}
