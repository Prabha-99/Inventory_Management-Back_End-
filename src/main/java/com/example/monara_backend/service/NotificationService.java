package com.example.monara_backend.service;

import com.example.monara_backend.common.Notification;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service

public class NotificationService implements Notification {

    private JavaMailSender emailSender;

    public NotificationService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void productAddNotification(String recipientEmail, String productName, String Category, String Quantity) throws MessagingException {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(recipientEmail);
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipientEmail);
        helper.setText("A new Product was added to the Inventory by Inventory_Admin. See below for further Details ");
        String tableContent = "<table style='border-collapse: collapse;'>" +
                "<tr>" +
                "<th style='border: 2px solid darkblue; padding: 8px;'>Category</th>" +
                "<th style='border: 2px solid darkblue; padding: 8px;'>Product</th>" +
                "<th style='border: 2px solid darkblue; padding: 8px;'>Quantity</th>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid darkblue; padding: 8px;'>" + Category + "</td>" +
                "<td style='border: 1px solid darkblue; padding: 8px;'>" + productName + "</td>" +
                "<td style='border: 1px solid darkblue; padding: 8px;'>" + Quantity + "</td>" +
                "</tr>" +
                "</table>";
        helper.setText(tableContent, true);
        emailSender.send(message);
    }


    @Override
    public void productDeleteNotification() {

    }

    @Override
    public void confirmedGINNotification() {

    }

    @Override
    public void rejectedGINNotification() {

    }

    @Override
    public void confirmedGRNNotification() {

    }


//    public void sendNotificationEmail(String recipientEmail, String productName, String Category,String Quantity) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(recipientEmail);
//        message.setSubject("New Product Added");
//        message.setText(Quantity + " "+ productName +"'s has been added into the inventory under the Category of "+Category);
//
//        emailSender.send(message);
//    }
}
