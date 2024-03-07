package com.example.dashboard.web.representation.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestUserSignIn(

        @NotNull
        @NotBlank
        String email,

        @NotNull
        @NotBlank
        String password
) {

}
