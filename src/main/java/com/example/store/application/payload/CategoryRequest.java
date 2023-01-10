package com.example.store.application.payload;

import lombok.Builder;

import java.util.Set;

@Builder
public record CategoryRequest(
        String name,

        Set<CategoryRequest> subCategories
) {
}
