package com.waldstonsantana.toncommerce.controllers;

import com.waldstonsantana.toncommerce.DTOs.product.ProductRequestDTO;
import com.waldstonsantana.toncommerce.DTOs.product.ProductResponseDto;
import com.waldstonsantana.toncommerce.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @PostMapping()
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDTO data) {
        ProductResponseDto response = service.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
