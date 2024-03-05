package com.example.dashboard.web.representation.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RequestUser(

        UUID id,

        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotNull
        String company_name) {
}
