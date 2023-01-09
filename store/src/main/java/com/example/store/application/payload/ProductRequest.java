package com.example.store.application.payload;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record ProductRequest(

        Long id,

        String name,
        BigDecimal price
) {
}
