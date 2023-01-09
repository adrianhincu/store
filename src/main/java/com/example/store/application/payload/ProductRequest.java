package com.example.store.application.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(

        @NotBlank
        String name,
        @NotNull
        @Min(value = 1)
        BigDecimal price
) {
}
