package com.example.store.application.controller;

import com.example.store.application.payload.CategoryRequest;
import com.example.store.application.payload.CategoryResponse;
import com.example.store.domain.service.CategoryService;
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
public class CategoryController {

    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/category", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@Validated @RequestBody CategoryRequest categoryRequest) {
        return categoryService.saveCategory(categoryRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/category/{id}", produces = "application/json")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/categories", produces = "application/json")
    public Page<CategoryResponse> getAllCategories(@RequestParam("page") int page,
                                                   @RequestParam("size") int size) {
        return categoryService.findAll(PageRequest.of(page, size));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/category/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void removeCategory(@PathVariable Long id) {
        categoryService.removeCategory(id);
    }

}
