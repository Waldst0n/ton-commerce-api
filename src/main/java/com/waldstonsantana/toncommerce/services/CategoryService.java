package com.waldstonsantana.toncommerce.services;

import com.waldstonsantana.toncommerce.DTOs.CategoryRequestDTO;
import com.waldstonsantana.toncommerce.DTOs.CategoryResponseDTO;
import com.waldstonsantana.toncommerce.model.Category;
import com.waldstonsantana.toncommerce.model.Product;
import com.waldstonsantana.toncommerce.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAll() {
        List<Category> categories = repository.findAll();

        return categories.stream()
                .map(category -> {
                    List<UUID> productIds = category.getProducts()
                            .stream()
                            .map(Product::getId)
                            .toList();

                    return new CategoryResponseDTO(
                            category.getId(),
                            category.getName(),
                            productIds
                    );
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryResponseDTO findById(UUID id) {
        Category category = repository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        List<UUID> productIds = category.getProducts().stream().map(product -> product.getId()).toList();
        return  new CategoryResponseDTO(category.getId(), category.getName(), productIds);
    }

    @Transactional
    public CategoryResponseDTO create(CategoryRequestDTO data) {

        Category category = new Category();
        category.setName(data.name());
        category = repository.save(category);
        return new CategoryResponseDTO(category.getId(), category.getName(), category.getProducts().stream().map(product -> product.getId()).toList());
    }


}
