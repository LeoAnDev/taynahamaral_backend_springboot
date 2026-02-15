package com.taynahamaral.confectionery.controller.user.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterUserRequest(

        @NotBlank(message = "Nome obrigatório")
        String name,

        @NotBlank(message = "E-Mail obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "Senha obrigatória")
        @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
        String password,

        @NotBlank(message = "Gênero obrigatório")
        String gender,

        @Past(message = "Data aniversário obrigatória e deve ser uma data passada")
        LocalDate birthDate,

        @NotBlank(message = "WhatsApp obrigatório")
        String whatsapp
) {}
