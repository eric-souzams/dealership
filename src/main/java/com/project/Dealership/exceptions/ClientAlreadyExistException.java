package com.project.Dealership.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientAlreadyExistException extends RuntimeException {

    public ClientAlreadyExistException(String message) {
        super(message);
    }

}