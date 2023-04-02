package com.example.monara_backend.controller;

import com.example.monara_backend.Request.NotificationEmail;
import com.example.monara_backend.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailNotificationController {
    @Autowired
    EmailNotificationService emailNotificationService;

    @PostMapping("/api/send")
    public ResponseEntity<String> sendNotificationEmail(@ModelAttribute NotificationEmail notificationEmail) {
        boolean emailSent = false;
        if (notificationEmail.getFile()!=null) {
            emailSent = emailNotificationService.sendNotificationEmailWithAttachment(notificationEmail, notificationEmail.getFile());
        } else {
            emailSent = emailNotificationService.sendTextNotificationEmail(notificationEmail);
        }
        if (emailSent) {
            return ResponseEntity.ok("Email sent.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while sending email.");
        }
    }
}
