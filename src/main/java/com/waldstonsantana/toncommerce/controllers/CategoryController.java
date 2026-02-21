package com.waldstonsantana.toncommerce.controllers;

import com.waldstonsantana.toncommerce.DTOs.CategoryRequestDTO;
import com.waldstonsantana.toncommerce.DTOs.CategoryResponseDTO;
import com.waldstonsantana.toncommerce.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping()
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        List<CategoryResponseDTO> categories = service.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable UUID id ) {

        CategoryResponseDTO category = service.findById(id);

        return ResponseEntity.ok().body(category);
    }

    @PostMapping()
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryRequestDTO data) {
        CategoryResponseDTO categoryResponseDTO = service.create(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDTO);
    }
}

