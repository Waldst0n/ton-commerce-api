package com.waldstonsantana.toncommerce.services;


import com.waldstonsantana.toncommerce.DTOs.product.ProductRequestDTO;
import com.waldstonsantana.toncommerce.DTOs.product.ProductResponseDto;
import com.waldstonsantana.toncommerce.model.Category;
import com.waldstonsantana.toncommerce.model.Product;
import com.waldstonsantana.toncommerce.repositories.CategoryRepository;
import com.waldstonsantana.toncommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private final CategoryRepository categoryRepository;

    @Transactional // Garante a integridade da transação com o banco
    public ProductResponseDto create(ProductRequestDTO data) {
        Product newProduct =  new Product();
        newProduct.setName(data.name());
        newProduct.setDescription(data.description());
        newProduct.setPrice(data.price());
        newProduct.setImgUrl(data.imgUrl());

        if (data.categoriesId() != null) {
            for (UUID categoryId : data.categoriesId()) {
                Category category = categoryRepository.getReferenceById(categoryId);
                newProduct.getCategories().add(category);
            }
        }

        newProduct = repository.save(newProduct);

        return new ProductResponseDto(
                newProduct.getId(),
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getPrice(),
                newProduct.getImgUrl(),
                newProduct.getCategories().stream().map(category -> category.getId()).toList()
        );
    }


}
