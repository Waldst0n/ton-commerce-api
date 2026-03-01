package com.waldstonsantana.toncommerce.DTOs.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserRequestDTO(

        @Size(min = 4, message = "O nome deve ter mais que 3 caracteres")
        String name,

        @Email(message = "Digite um email válido")
        @NotBlank(message = "O campo email é obrigatório")
        String email,

        String phone,

        @JsonProperty("birth_date")
        LocalDate birthDate,

        @Size(min = 6, message = "O passoerd deve ter no mínimo 6 caracteres")
        String password
) {
}
