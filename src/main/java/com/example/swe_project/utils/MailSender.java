package com.example.swe_project.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailSender {
    @Bean(name = "customMailSender")
    public JavaMailSender customMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587); // Port for STARTTLS
        mailSender.setUsername("medet.t2003@gmail.com");
        mailSender.setPassword("siohyurjiixtlkuy"); // Replace with your App Password

        // Set additional properties
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
        props.put("mail.debug", "true"); // Enable debug logs for troubleshooting

        return mailSender;
    }

    @Bean
    SimpleMailMessage templateApprovalMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("medet.t2003@gmail.com");
        message.setSubject("Approval of your account");
        return message;
    }
}
