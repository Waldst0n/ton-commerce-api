package com.waldstonsantana.toncommerce.controllers;

import com.waldstonsantana.toncommerce.DTOs.user.UserRequestDTO;
import com.waldstonsantana.toncommerce.DTOs.user.UserResponseDTO;
import com.waldstonsantana.toncommerce.services.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAll(Pageable pageable) {
        Page<UserResponseDTO> users = service.findAll(pageable);

        return  ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable UUID id) {
        UserResponseDTO user = service.findById(id);

        return  ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO data) {
        UserResponseDTO user = service.create(data);

        return  ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
