package com.example.dashboard.web.representation.request.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestUser(

        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        String company_name
) {
}
