package com.example.monara_backend.service;

import com.example.monara_backend.Request.NotificationEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@Slf4j
public class EmailNotificationServiceImpl implements EmailNotificationService{

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public boolean sendTextNotificationEmail (NotificationEmail notificationEmail) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(notificationEmail.getTo());
            mailMessage.setSubject(notificationEmail.getSubject());
            mailMessage.setText(notificationEmail.getBodyMsg());
            mailSender.send(mailMessage);
            return true;
        } catch (Exception ex) {
            log.info(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean sendNotificationEmailWithAttachment (NotificationEmail notificationEmail, MultipartFile file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(notificationEmail.getTo());
            messageHelper.setSubject(notificationEmail.getSubject());

            if (notificationEmail.isHtmlMsg()) {
                //for html based text message set flag to true
                //bodyMsg: <b>This is Notification email</b>
                messageHelper.setText(notificationEmail.getBodyMsg(), true);
            } else {
                ///for simple plain text message
                messageHelper.setText(notificationEmail.getBodyMsg());
            }

            messageHelper.addAttachment(file.getOriginalFilename(), file);
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException ex) {
            log.info(ex.getMessage());
            return false;
        }
    }
}
