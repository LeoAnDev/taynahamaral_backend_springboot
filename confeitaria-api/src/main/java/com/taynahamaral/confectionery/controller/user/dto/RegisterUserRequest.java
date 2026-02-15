package com.taynahamaral.confectionery.controller.user.dto;

import com.taynahamaral.confectionery.domain.profile.Gender;
import com.taynahamaral.confectionery.validation.UniqueEmail;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record RegisterUserRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @UniqueEmail
        String email,

        @NotBlank(message = "Password is required")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
                message = "Password must contain at least 8 characters, one uppercase, one lowercase, one number, and one special character"
        )
        String password,

        @NotNull(message = "Gender is required")
        Gender gender,

        @Past(message = "Birth date must be in the past")
        LocalDate birthDate,

        @NotBlank(message = "WhatsApp is required")
        String whatsapp
) {}
