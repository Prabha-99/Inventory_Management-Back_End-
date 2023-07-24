package com.example.monara_backend.common;

import jakarta.mail.MessagingException;

public interface Notification {
    void productAddNotification(String recipientEmail, String productName, String Category,String Quantity) throws MessagingException;
    void productDeleteNotification(String recipientEmail, String productName, String Category) throws MessagingException;
    void GINNotification(String recipientEmail,String Path) throws MessagingException;
    void GRNNotification(String recipientEmail,String Path) throws MessagingException;
    void confirmedGRNNotification();
}
