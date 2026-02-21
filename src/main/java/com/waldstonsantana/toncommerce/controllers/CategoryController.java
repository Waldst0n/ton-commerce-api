package com.waldstonsantana.toncommerce.controllers;

import com.waldstonsantana.toncommerce.DTOs.CategoryResponseDTO;
import com.waldstonsantana.toncommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        List<CategoryResponseDTO> categories = service.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@RequestParam UUID id ) {

        CategoryResponseDTO category = service.findById(id);

        return ResponseEntity.ok().body(category);


    }
}

