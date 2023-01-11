package com.example.store.domain.service;

import com.example.store.application.payload.ModifyProductRequest;
import com.example.store.application.payload.ProductRequest;
import com.example.store.application.payload.ProductResponse;
import com.example.store.domain.exceptions.CategoryNotFoundException;
import com.example.store.domain.exceptions.ProductNotFoundException;
import com.example.store.domain.mapper.ProductMapper;
import com.example.store.domain.model.Category;
import com.example.store.domain.model.Product;
import com.example.store.domain.repository.CategoryRepository;
import com.example.store.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return productRepository.findById(id).map(productMapper::mapProduct)
                .orElseThrow(() -> new ProductNotFoundException("Product not found for id " + id));
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> findAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).map(productMapper::mapProduct);
    }

    @Transactional
    public ProductResponse modifyProduct(ModifyProductRequest modifyProductRequest) {
        Long productId = modifyProductRequest.productId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found for id " + productId));
        product.setPrice(modifyProductRequest.newPrice());
        return productMapper.mapProduct(product);
    }

    @Transactional
    public ProductResponse saveProduct(ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.categoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found for id " + productRequest.categoryId()));
        Product product = productMapper.mapProductRequest(productRequest);
        category.addProduct(product);
        return productMapper.mapProduct(productRepository.save(product));
    }

    @Transactional
    public void removeProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found for id " + id));
        productRepository.deleteById(id);
    }
}
