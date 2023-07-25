package com.example.monara_backend.controller;

import com.example.monara_backend.Configuration.AuthenticationRequest;
import com.example.monara_backend.Configuration.AuthenticationResponse;
import com.example.monara_backend.Configuration.RegisterRequest;
import com.example.monara_backend.dto.navBarLogin;
import com.example.monara_backend.model.User;
import com.example.monara_backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    @Autowired
    PasswordEncoder passwordEncoder;

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

    @PutMapping("/UpdateProfile")
    public ResponseEntity<User> updateUserProfile (@RequestBody User updatedUser){
        int id = updatedUser.getId();
        User users = authenticationService.getUserById(id);

        if (users != null) {
            // Update specific fields based on the values in the updatedUser object
            if (updatedUser.getFirstname() != null) {
                users.setFirstname(updatedUser.getFirstname());
            }
            if (updatedUser.getLastname() != null) {
                users.setLastname(updatedUser.getLastname());
            }
            if (updatedUser.getEmail() != null) {
                users.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPassword() != null){
                users.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            if (updatedUser.getRole() != null) {
                users.setRole(updatedUser.getRole());
            }
            User updatedUserEntity = authenticationService.updateUser(users);
            return ResponseEntity.ok(updatedUserEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
