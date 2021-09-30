package com.project.Dealership.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class SaleCannotBeMadeException extends RuntimeException {

    public SaleCannotBeMadeException(String message) {
        super(message);
    }

}