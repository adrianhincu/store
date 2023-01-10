package com.example.store.application.payload;

import lombok.Builder;

@Builder
public record CategoryRequest(
        String name
) {
}
