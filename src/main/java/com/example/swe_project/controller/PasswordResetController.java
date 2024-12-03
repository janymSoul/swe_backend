package com.example.swe_project.controller;

import com.example.swe_project.dto.ResetPasswordDto;
import com.example.swe_project.entity.PasswordResetToken;
import com.example.swe_project.entity.User;
import com.example.swe_project.repo.PasswordResetTokenRepository;
import com.example.swe_project.repo.UserRepository;
import com.example.swe_project.services.MailService;
import com.example.swe_project.services.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class PasswordResetController {

    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetService passwordResetService;
    public PasswordResetController(PasswordResetTokenRepository tokenRepository,
                                   UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordResetService passwordResetService) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/ask-reset")
    public void sendEmail(@RequestParam String email){
        passwordResetService.sendPasswordResetLink(email);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@ModelAttribute ResetPasswordDto resetPasswordDto) {
        PasswordResetToken resetToken = tokenRepository.findByToken(resetPasswordDto.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        User user = resetToken.getUser();
        if (!passwordEncoder.matches(resetPasswordDto.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }
        user.setPassword(resetPasswordDto.getNewPassword()); // Hash the password before saving
        userRepository.save(user);

        tokenRepository.delete(resetToken); // Delete the token after use
        return ResponseEntity.ok("Password reset successful");
    }
}
