package com.clothesshop.dto;

import jakarta.validation.constraints.NotNull;

public record UserLogin(
        @NotNull(message = "Username can not be empty")
        String username,
        @NotNull @NotNull(message = "Username can not be empty")
        String password
) {
}
