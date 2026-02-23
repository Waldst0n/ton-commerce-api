package com.waldstonsantana.toncommerce.DTOs.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;


public record ProductResponseDto(
        UUID id,
        String name,
        String description,
        Double price,
        @JsonProperty("img_url")
        String imgUrl,

        @JsonProperty(value = "categories_id")
        List<UUID> categoriesId
) {
}
