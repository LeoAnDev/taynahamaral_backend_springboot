package com.taynahamaral.confectionery.controller.user.dto;

import com.taynahamaral.confectionery.domain.profile.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterUserRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must have at least 6 characters")
        String password,

        @NotNull(message = "Gender is required")
        Gender gender,

        @Past(message = "Birth date must be in the past")
        LocalDate birthDate,

        @NotBlank(message = "WhatsApp is required")
        String whatsapp
) {}
