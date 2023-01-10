package com.example.store.domain.advice;

import com.example.store.domain.exceptions.StoreApiError;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;

    public static ErrorResponse getStoreApiError(StoreApiError storeApiError) {
        return ErrorResponse.builder()
                .errorMessage(storeApiError.getErrorMessage())
                .errorCode(storeApiError.name())
                .build();
    }
}
