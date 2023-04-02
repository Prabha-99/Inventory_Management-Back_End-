package com.example.monara_backend.service;

import com.example.monara_backend.Request.NotificationEmail;
import org.springframework.web.multipart.MultipartFile;

public interface EmailNotificationService {
    boolean sendTextNotificationEmail(NotificationEmail notificationEmail);
    boolean sendNotificationEmailWithAttachment(NotificationEmail notificationEmail,
                                                MultipartFile file);
}
