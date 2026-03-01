package com.waldstonsantana.toncommerce.services;

import com.waldstonsantana.toncommerce.DTOs.user.UserRequestDTO;
import com.waldstonsantana.toncommerce.DTOs.user.UserResponseDTO;
import com.waldstonsantana.toncommerce.exception.UserNotFoundException;
import com.waldstonsantana.toncommerce.model.Order;
import com.waldstonsantana.toncommerce.model.User;
import com.waldstonsantana.toncommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);

        return users.map(u -> {
            List<UUID> orderIds = u.getOrders()
                    .stream()
                    .map(Order::getId).toList();
            return new UserResponseDTO(
                    u.getId(),
                    u.getName(),
                    u.getEmail(),
                    u.getPhone(),
                    u.getBirthDate(),
                    orderIds
            );

        });
    }

    public UserResponseDTO findById(UUID id) {
        User user = findUserById(id);

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthDate(),
                user.getOrders().stream().map(o -> o.getId()).toList()

        );
    }

    @Transactional
    public UserResponseDTO create(UserRequestDTO data) {
        User user = new User();

        user.setName(data.name());
        user.setEmail(data.email());
        user.setPhone(data.phone());
        user.setBirthDate(data.birthDate());
        user.setPassword(data.password());

        user = repository.save(user);

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthDate(),
                user.getOrders().stream().map(order -> order.getId()).toList()

        );
    };

    @Transactional
    public UserResponseDTO update(UUID id, UserRequestDTO data) {

        User user = findUserById(id);
        user.setName(data.name());
        user.setEmail(data.email());
        user.setPhone(data.phone());
        user.setBirthDate(data.birthDate());

        user = repository.save(user);

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthDate(),
                user.getOrders().stream().map(o -> o.getId()).toList()
        );

    }

    @Transactional
    public void delete(UUID id) {
        User user = findUserById(id);
        repository.delete(user);
    }

    private User findUserById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
    }







}
