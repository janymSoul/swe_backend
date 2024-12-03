package com.example.swe_project.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("medet.t2003@gmail.com");
        javaMailSender.send(message);
    }

    public void sendPasswordResetEmail(String to, String resetLink, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset Request");
        message.setText("Click the link below to reset your password:\n" + resetLink + "\n Copy the token and paste it in the page opened by link\n" + token);

        message.setFrom("medet.t2003@gmail.com");
        javaMailSender.send(message);
    }
}
