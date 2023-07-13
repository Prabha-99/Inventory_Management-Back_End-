package com.example.monara_backend.service;

import com.example.monara_backend.model.PasswordResetToken;
import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.PasswordResetTokenRepository;
import com.example.monara_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    JavaMailSender mailSender;

    public void initiatePasswordReset(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();
        LocalDateTime expirationDateTime = LocalDateTime.now().plusHours(24);

        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .token(token)
                .user(user)
                .expirationDateTime(expirationDateTime)
                .build();

        passwordResetTokenRepository.save(passwordResetToken);

        sendResetPasswordEmail(user.getEmail(), token);
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (passwordResetToken.getExpirationDateTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token has expired");
        }

        User user = passwordResetToken.getUser();
        user.setPassword(newPassword);
        userRepo.save(user);

        passwordResetTokenRepository.delete(passwordResetToken);
    }

    private void sendResetPasswordEmail(String email, String token) {
        String resetPasswordLink = "http://your-frontend-app/reset-password?token=" + token;
        String emailBody = "Click the following link to reset your password: " + resetPasswordLink;

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Reset Password");
        mailMessage.setText(emailBody);

        mailSender.send(mailMessage);
    }



}
