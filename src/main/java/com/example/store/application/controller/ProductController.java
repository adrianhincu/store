package com.example.store.application.controller;

import com.example.store.application.payload.ModifyProductRequest;
import com.example.store.application.payload.ProductRequest;
import com.example.store.application.payload.ProductResponse;
import com.example.store.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/product", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@Validated @RequestBody ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(path = "/product/{id}", produces = "application/json")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(path = "/products", produces = "application/json")
    public Page<ProductResponse> getAllProducts(@RequestParam("page") int page,
                                                @RequestParam("size") int size) {
        return productService.findAll(PageRequest.of(page, size));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/product", produces = "application/json")
    public ProductResponse modifyProduct(@RequestBody @Validated ModifyProductRequest modifyProductRequest) {
        return productService.modifyProduct(modifyProductRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/product/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void removeProduct(@PathVariable Long id) {
        productService.removeProduct(id);
    }

}
