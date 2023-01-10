package com.example.store.utils;

import com.example.store.application.payload.ProductRequest;
import com.example.store.application.payload.ProductResponse;
import com.example.store.domain.model.Product;

import java.math.BigDecimal;

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
                .build();
    }

    public static ProductRequest getMonitorProductRequest() {
        return ProductRequest.builder()
                .name("Monitor")
                .price(BigDecimal.valueOf(50L))
                .build();
    }


}



