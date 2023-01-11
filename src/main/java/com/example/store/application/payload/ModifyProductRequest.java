package com.example.store.application.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ModifyProductRequest(

        @NotNull
        Long productId,
        @NotNull
        @Min(value = 1)
        BigDecimal newPrice

) {
}
