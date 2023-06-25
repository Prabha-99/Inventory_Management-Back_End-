package com.example.monara_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private JavaMailSender emailSender;

    @Autowired
    public NotificationService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendNotificationEmail(String recipientEmail, String productName, String Category,String Quantity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("New Product Added");
        message.setText(Quantity + " "+ productName +"'s has been added into the inventory under the Category of "+Category);

        emailSender.send(message);
    }
}
