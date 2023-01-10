package com.example.store.domain.service;

import com.example.store.application.payload.CategoryRequest;
import com.example.store.application.payload.CategoryResponse;
import com.example.store.domain.exceptions.ProductNotFoundException;
import com.example.store.domain.mapper.CategoryMapper;
import com.example.store.domain.model.Category;
import com.example.store.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::mapCategory)
                .orElseThrow(() -> new ProductNotFoundException("Product not found for id " + id));
    }

    @Transactional(readOnly = true)
    public Page<CategoryResponse> findAll(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest).map(categoryMapper::mapCategory);
    }

    @Transactional
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryRepository.save(categoryMapper.mapCategoryRequest(categoryRequest));
        return categoryMapper.mapCategory(category);
    }
}
