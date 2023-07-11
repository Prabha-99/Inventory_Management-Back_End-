package com.example.monara_backend.controller;

import com.example.monara_backend.dto.GetPasswordDto;
import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/password")
public class PasswordController {
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    UserRepo userRepo;

    @PostMapping("/getpassword")
    public void retrievePassword(@RequestBody GetPasswordDto getPasswordDto){
        String email = getPasswordDto.getEmail();
        Optional<User> user = userRepo.findByEmail(email);

        if (user.isPresent()){
            User users = user.get();
            String password = users.getPassword();

            sendPasswordByEmail(email , password);
        }
    }
    private void sendPasswordByEmail(String email, String password) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Password Retrieval");
            message.setText("Your password is: " + password);

            // Send the email
            mailSender.send(message);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
