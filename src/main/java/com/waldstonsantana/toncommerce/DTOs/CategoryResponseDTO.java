package com.waldstonsantana.toncommerce.DTOs;

import java.util.List;
import java.util.UUID;

public record CategoryResponseDTO(
        UUID id,
        String name,
        List<UUID> productsId
) {
}
