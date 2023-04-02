package com.example.monara_backend.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {

    private String to;
    private String cc;
    private String subject;
    private String bodyMsg;
    private MultipartFile file;
    private boolean isHtmlMsg;
}
