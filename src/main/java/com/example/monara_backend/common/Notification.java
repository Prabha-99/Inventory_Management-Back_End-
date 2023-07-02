package com.example.monara_backend.common;

import jakarta.mail.MessagingException;

public interface Notification {

    void productAddNotification(String recipientEmail, String productName, String Category,String Quantity) throws MessagingException;
    void productDeleteNotification();
    void GINNotification(String recipientEmail,String Path) throws MessagingException;
    void GRNNotification(String recipientEmail, String productName, String Category,String Quantity,String Path) throws MessagingException;
    void confirmedGRNNotification();

}
