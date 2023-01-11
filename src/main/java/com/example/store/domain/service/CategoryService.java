package com.example.store.domain.service;

import com.example.store.application.payload.CategoryRequest;
import com.example.store.application.payload.CategoryResponse;
import com.example.store.domain.exceptions.CategoryNotFoundException;
import com.example.store.domain.mapper.CategoryMapper;
import com.example.store.domain.model.Category;
import com.example.store.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::mapCategory)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found for id " + id));
    }

    @Transactional(readOnly = true)
    public Page<CategoryResponse> findAll(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest).map(categoryMapper::mapCategory);
    }

    @Transactional
    public void removeCategory(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found for id " + id));
        categoryRepository.deleteById(id);
    }

    @Transactional
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.mapCategoryRequest(categoryRequest);
        Optional.ofNullable(categoryRequest.subCategories()).ifPresent(subCategories ->
                subCategories.forEach(subCategory ->
                        category.addSubCategory(categoryMapper.mapCategoryRequest(subCategory))
                ));
        return categoryMapper.mapCategory(categoryRepository.save(category));
    }
}
