package com.example.store.application.payload;

import lombok.Builder;

import java.util.Set;

@Builder
public record CategoryResponse(

        Long id,
        String name,

        Set<CategoryResponse> children,

        Set<ProductResponse> products

) {
}
