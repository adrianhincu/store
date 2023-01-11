package com.example.store.domain.advice;

import com.example.store.domain.exceptions.CategoryNotFoundException;
import com.example.store.domain.exceptions.ProductNotFoundException;
import com.example.store.domain.exceptions.StoreApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class StoreApiExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
        log.warn(e.getMessage(), e);
        return ResponseEntity
                .status(StoreApiError.PRODUCT_NOT_FOUND.getHttpStatus())
                .body(ErrorResponse.getStoreApiError(StoreApiError.PRODUCT_NOT_FOUND));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException e) {
        log.warn(e.getMessage(), e);
        return ResponseEntity
                .status(StoreApiError.CATEGORY_NOT_FOUND.getHttpStatus())
                .body(ErrorResponse.getStoreApiError(StoreApiError.CATEGORY_NOT_FOUND));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.warn(e.getMessage(), e);
        return ResponseEntity
                .status(StoreApiError.INVALID_GRANTED_AUTHORITIES.getHttpStatus())
                .body(ErrorResponse.getStoreApiError(StoreApiError.INVALID_GRANTED_AUTHORITIES));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
        log.warn(e.getMessage(), e);
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
                .status(StoreApiError.UNPROCESSABLE_ENTITY.getHttpStatus())
                .body(ErrorResponse.getStoreApiError(StoreApiError.UNPROCESSABLE_ENTITY, errors));

    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ErrorResponse> handleDefaultException(RuntimeException e) {
        log.warn(e.getMessage(), e);
        return ResponseEntity
                .status(StoreApiError.DEFAULT.getHttpStatus())
                .body(ErrorResponse.getStoreApiError(StoreApiError.DEFAULT));
    }
}
