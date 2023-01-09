package com.example.store.domain.service;

import com.example.store.application.payload.ProductRequest;
import com.example.store.application.payload.ProductResponse;
import com.example.store.domain.exceptions.ProductNotFoundException;
import com.example.store.domain.mapper.ProductMapper;
import com.example.store.domain.model.Product;
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
    private final ProductMapper productMapper;

    public ProductResponse findById(Long id) {
        return productRepository.findById(id).map(productMapper::mapProduct)
                .orElseThrow(() -> new ProductNotFoundException());
    }

    public Page<ProductResponse> findAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).map(productMapper::mapProduct);
    }

    @Transactional
    public ProductResponse saveProduct(ProductRequest productRequest) {
        Product product = productRepository.save(productMapper.mapProductRequest(productRequest));
        return productMapper.mapProduct(product);
    }
}
