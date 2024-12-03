package com.example.swe_project.services;


import com.example.swe_project.entity.PasswordResetToken;
import com.example.swe_project.entity.User;
import com.example.swe_project.repo.UserRepository;
import com.example.swe_project.repo.PasswordResetTokenRepository;
import com.example.swe_project.services.MailService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final MailService mailService;

    public PasswordResetService(UserRepository userRepository,
                                PasswordResetTokenRepository tokenRepository,
                                MailService mailService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    public void sendPasswordResetLink(String email) {
        User user = userRepository.findByEmail(email).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1));

        tokenRepository.save(resetToken);

        String resetLink = "http://localhost:3000/api/reset-password";
        mailService.sendPasswordResetEmail(user.getEmail(), resetLink, token);
    }
}
