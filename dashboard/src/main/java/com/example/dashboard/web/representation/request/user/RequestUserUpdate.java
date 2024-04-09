package com.example.dashboard.web.representation.request.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RequestUserUpdate(

        @NotBlank
        String password
) {
}
