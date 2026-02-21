package com.waldstonsantana.toncommerce.repositories;

import com.waldstonsantana.toncommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
