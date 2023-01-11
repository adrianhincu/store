package com.example.store.application.payload;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ProductResponse(

        Long id,
        String name,
        BigDecimal price,

        LocalDateTime createdDate,

        LocalDateTime lastUpdatedDate,

        CategoryResponse category
) {
}
