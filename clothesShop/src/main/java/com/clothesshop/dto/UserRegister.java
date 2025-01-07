package com.clothesshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegister(
        @NotBlank(message = "Username can not be empty")
        @Size(max = 20)
        String username,
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password,
        @NotBlank(message = "repeatPassword is required")
        @Size(min = 6, message = "repeatPassword must be at least 6 characters long")
        String repeatPassword,
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email address")
        @Size(max = 50)
        String email

) {
    public static UserRegister defaultInstance() {
        return new UserRegister("", "", "", "");
    }
}
