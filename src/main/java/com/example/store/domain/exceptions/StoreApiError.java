package com.example.store.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StoreApiError {

    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product Not Found"),
    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    StoreApiError(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }


}
