package com.waldstonsantana.toncommerce.services;


import com.waldstonsantana.toncommerce.DTOs.product.ProductRequestDTO;
import com.waldstonsantana.toncommerce.DTOs.product.ProductResponseDto;
import com.waldstonsantana.toncommerce.exception.ProductNotFoundException;
import com.waldstonsantana.toncommerce.model.Category;
import com.waldstonsantana.toncommerce.model.Product;
import com.waldstonsantana.toncommerce.repositories.CategoryRepository;
import com.waldstonsantana.toncommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private final CategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    public Page<ProductResponseDto> findAll(Pageable pageable) {
        Page<Product> productsPaginated = repository.findAll(pageable);

        return productsPaginated.map(p -> {
            List<UUID> categoriesIds = p.getCategories()
                    .stream()
                    .map(Category::getId)
                    .toList();

            return new ProductResponseDto(
                    p.getId(),
                    p.getName(),
                    p.getDescription(),
                    p.getPrice(),
                    p.getImgUrl(),
                    categoriesIds
            );
        });
    }

    @Transactional(readOnly = true)
    public ProductResponseDto findById(UUID id) {
        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado."));

        return  new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl(),
                product.getCategories().stream().map(category -> category.getId()).toList());
    }

    @Transactional
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

    @Transactional
    public ProductResponseDto update(UUID id, ProductRequestDTO data) {
        Product product = findProductById(id);
        product.setName(data.name());
        product.setDescription(data.description());
        product.setImgUrl(data.imgUrl());
        product.setPrice(data.price());

        product = repository.save(product);

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl(),
                product.getCategories()
                        .stream()
                        .map(c -> c.getId()).toList());
    }

    @Transactional(readOnly = true)
    private Product findProductById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado."));
    }

    @Transactional
    public void delete(UUID id) {
        Product product = findProductById(id);
        repository.delete(product);
    }


}
