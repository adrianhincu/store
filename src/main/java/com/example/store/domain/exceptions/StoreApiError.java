package com.example.store.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StoreApiError {

    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product Not Found"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "Category Not Found"),
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "Validation failed %s"),
    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Contact Administrator"),
    INVALID_GRANTED_AUTHORITIES(HttpStatus.FORBIDDEN, "YOU DONT HAVE THE RIGHTS TO DO THAT");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    StoreApiError(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }


}
