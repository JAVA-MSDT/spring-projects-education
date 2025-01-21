package com.clothesshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordUpdate(
        long id,
        @NotBlank(message = "Current Password is wrong")
        String currentPassword,
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String newPassword,
        @NotBlank(message = "repeatPassword is required")
        @Size(min = 6, message = "repeatPassword must be at least 6 characters long")
        String repeatPassword
) {
    public static PasswordUpdate defaultInstance() {
        return new PasswordUpdate(0L, "", "", "");
    }
}
