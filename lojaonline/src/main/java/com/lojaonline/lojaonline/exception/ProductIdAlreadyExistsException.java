package com.lojaonline.lojaonline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductIdAlreadyExistsException extends RuntimeException {
    public ProductIdAlreadyExistsException(String message) {
        super(message);
    }
}