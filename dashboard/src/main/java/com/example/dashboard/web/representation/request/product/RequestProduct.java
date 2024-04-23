package com.example.dashboard.web.representation.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RequestProduct(

        @NotNull
        UUID id_user,
        @NotBlank
        String title,
        @NotNull
        int amount,
        @NotNull
        double price,
        @NotBlank
        String description
) {

}
