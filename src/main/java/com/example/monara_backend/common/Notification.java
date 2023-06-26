package com.example.monara_backend.common;

public interface Notification {

    void productAddNotification(String recipientEmail, String productName, String Category,String Quantity);
    void productDeleteNotification();
    void confirmedGINNotification();
    void rejectedGINNotification();
    void confirmedGRNNotification();

}
