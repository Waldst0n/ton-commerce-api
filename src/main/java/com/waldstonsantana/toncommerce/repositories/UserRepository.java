package com.waldstonsantana.toncommerce.repositories;

import com.waldstonsantana.toncommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
