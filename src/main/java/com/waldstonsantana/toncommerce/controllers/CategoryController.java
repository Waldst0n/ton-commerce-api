package com.waldstonsantana.toncommerce.controllers;

import com.waldstonsantana.toncommerce.DTOs.category.CategoryRequestDTO;
import com.waldstonsantana.toncommerce.DTOs.category.CategoryResponseDTO;
import com.waldstonsantana.toncommerce.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<CategoryResponseDTO>> findAll(Pageable pageable) {
        Page<CategoryResponseDTO> categories = service.findAll(pageable);
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

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody CategoryRequestDTO data) {
        CategoryResponseDTO categoryResponseDTO = service.update(id, data);

        return ResponseEntity.ok(categoryResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

