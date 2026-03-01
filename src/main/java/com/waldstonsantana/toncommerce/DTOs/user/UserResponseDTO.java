package com.waldstonsantana.toncommerce.DTOs.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String phone,
        @JsonProperty("birth_date")
        LocalDate birthDate,
        List<UUID> ordersId
) {
}
