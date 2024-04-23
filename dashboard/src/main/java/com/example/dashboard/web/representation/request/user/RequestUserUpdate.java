package com.example.dashboard.web.representation.request.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RequestUserUpdate(

        @NotNull
        String id_user,
        @NotBlank
        String password
) {
}
