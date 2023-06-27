package com.example.monara_backend.service;

import com.example.monara_backend.common.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class NotificationService implements Notification {

    private JavaMailSender emailSender;

    public NotificationService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void productAddNotification(String recipientEmail, String productName, String Category, String Quantity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("New Product Added");
//        message.setText(Quantity + " "+ productName +"'s has been added into the inventory under the Category of "+Category);
        message.setText("------------------------------------------------------------------------\n" +
                        "|       Category       |        Product        |       Heading 3       |\n" +
                        "------------------------------------------------------------------------\n" +
                        "|"    + Category+     "|"     + productName+  "|"    + Quantity+      "|\n" +
                        "------------------------------------------------------------------------\n");

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
