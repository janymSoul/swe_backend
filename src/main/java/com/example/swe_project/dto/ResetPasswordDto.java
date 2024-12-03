package com.example.swe_project.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String token;
    private String oldPassword;
    private String newPassword;
}
