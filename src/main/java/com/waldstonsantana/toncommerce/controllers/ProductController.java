package com.waldstonsantana.toncommerce.controllers;

import com.waldstonsantana.toncommerce.DTOs.product.ProductRequestDTO;
import com.waldstonsantana.toncommerce.DTOs.product.ProductResponseDto;
import com.waldstonsantana.toncommerce.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> findAll(Pageable pageable) {
        Page<ProductResponseDto> products = service.findAll(pageable);

       return  ResponseEntity.ok().body(products);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable UUID id) {
        ProductResponseDto product = service.findById(id);

        return ResponseEntity.ok().body(product);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDTO data) {
        ProductResponseDto response = service.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable UUID id, @Valid @RequestBody ProductRequestDTO data) {
        ProductResponseDto productResponseDto = service.update(id, data);

        return ResponseEntity.ok(productResponseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
