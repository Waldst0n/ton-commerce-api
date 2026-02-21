package com.waldstonsantana.toncommerce.DTOs;

import com.waldstonsantana.toncommerce.model.Product;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record CategoryRequestDTO(
        @NotBlank
        String name,
        List<UUID> productsId
) {
}
