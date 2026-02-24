package com.waldstonsantana.toncommerce.DTOs.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record CategoryRequestDTO(
        @NotBlank
        String name,

        List<UUID> productsId
) {
}
