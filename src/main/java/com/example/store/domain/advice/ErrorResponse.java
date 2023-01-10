package com.example.store.domain.advice;

import com.example.store.domain.exceptions.StoreApiError;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

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

    public static ErrorResponse getStoreApiError(StoreApiError storeApiError, Map<String, String> errors) {
        return ErrorResponse.builder()
                .errorMessage(String.format(storeApiError.getErrorMessage(),errors))
                .errorCode(storeApiError.name())
                .build();
    }
}
