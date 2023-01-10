package com.example.store.domain.mapper;

import com.example.store.application.payload.CategoryRequest;
import com.example.store.application.payload.CategoryResponse;
import com.example.store.application.payload.ProductResponse;
import com.example.store.domain.model.Category;
import com.example.store.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "version", ignore = true)
    Category mapCategoryRequest(CategoryRequest productRequest);

    CategoryResponse mapCategory(Category category);

    @Mapping(target = "category", ignore = true)
    ProductResponse mapProduct(Product product);


}