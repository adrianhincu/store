package com.example.store.application.controller;

import com.example.store.application.payload.CategoryRequest;
import com.example.store.application.payload.CategoryResponse;
import com.example.store.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(path = "/category", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createProduct(@Validated @RequestBody CategoryRequest categoryRequest) {
        return categoryService.saveCategory(categoryRequest);
    }

//    @GetMapping(path = "/product/{id}", produces = "application/json")
//    public ProductResponse getProductById(@PathVariable Long id) {
//        return productService.findById(id);
//    }
//
//    @GetMapping(path = "/allProducts", produces = "application/json")
//    public Page<ProductResponse> getAllProducts(@RequestParam("page") int page,
//                                                @RequestParam("size") int size) {
//        return productService.findAll(PageRequest.of(page, size));
//    }

}
