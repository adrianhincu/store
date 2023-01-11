package com.example.store.application.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record CategoryRequest(
        @NotBlank
        String name,

        Set<CategoryRequest> subCategories
) {
}
