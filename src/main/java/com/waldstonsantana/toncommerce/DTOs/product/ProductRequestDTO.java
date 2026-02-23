package com.waldstonsantana.toncommerce.DTOs.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record ProductRequestDTO(

        @NotBlank
        @Size(min = 3, message = "O nome do produto deve ter no mínimo 3 caracteres")
        String name,

        @NotBlank
        @Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres")
        String description,

        @NotNull
        @Positive(message = "O valor não pode ser menor que 0")
        Double price,

        @JsonProperty("img_url")
        @NotBlank
        String imgUrl,

        @JsonProperty("categories_id")
        List<UUID> categoriesId
) {
}
