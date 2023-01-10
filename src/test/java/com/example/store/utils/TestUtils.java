package com.example.store.utils;

import com.example.store.application.payload.CategoryRequest;
import com.example.store.application.payload.ProductRequest;
import com.example.store.application.payload.ProductResponse;
import com.example.store.domain.model.Product;

import java.math.BigDecimal;
import java.util.Set;

public class TestUtils {
    public static String getProductNotFoundResponse() {
        return """
                {
                 "errorCode": "PRODUCT_NOT_FOUND",
                 "errorMessage": "Product Not Found"
                }
                 """;
    }

    public static String getValidProduct() {
        return """ 
                {
                "id": 1,
                "name": "Laptop",
                "price": 100
                }
                 """;
    }

    public static ProductResponse getProductResponse() {
        return ProductResponse.builder()
                .id(1L)
                .name("Laptop")
                .price(BigDecimal.valueOf(100L)).build();
    }

    public static Product getProduct() {
        return Product.builder()
                .name("Laptop")
                .price(BigDecimal.valueOf(100L))
                .build();
    }

    public static ProductRequest getLaptopProductRequest() {
        return ProductRequest.builder()
                .name("Laptop")
                .price(BigDecimal.valueOf(100L))
                .categoryId(1L)
                .build();
    }

    public static ProductRequest getMonitorProductRequest() {
        return ProductRequest.builder()
                .name("Telefon")
                .price(BigDecimal.valueOf(50L))
                .build();
    }

    public static CategoryRequest getCategoryRequest() {
        return CategoryRequest.builder()
                .name("Laptop, Tablete & Telefoane")
                .subCategories(Set.of(
                        CategoryRequest.builder()
                                .name("Laptopuri")
                                .build(),
                        CategoryRequest.builder()
                                .name("Tablete")
                                .build(),
                        CategoryRequest.builder()
                                .name("Telefoane")
                                .build()
                ))
                .build();
    }


}



