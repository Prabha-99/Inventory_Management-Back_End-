package com.example.monara_backend.controller;

import com.example.monara_backend.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/mail")
public class EmailSendControl {


        @Autowired
        private EmailServiceImpl emailServiceImpl;

        @PostMapping("/send")
        public ResponseEntity<String> sendEmailWithAttachment(@RequestParam("to") String to,
                                                              @RequestParam("subject") String subject,
                                                              @RequestParam("bodyMsg") String bodyMsg,
                                                              @RequestParam("file") File file) {
            try {
                emailServiceImpl.sendEmailWithAttachment(to, subject, bodyMsg, file);
                return ResponseEntity.ok("Email sent successfully!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
            }
        }
    }
