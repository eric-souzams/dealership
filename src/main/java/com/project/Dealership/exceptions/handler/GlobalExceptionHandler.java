package com.project.Dealership.exceptions.handler;

import com.project.Dealership.exceptions.*;
import com.project.Dealership.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<ErrorDescription.Field> fields = new ArrayList<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new ErrorDescription.Field(name, message));
        }

        ErrorDescription errorDescription = ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title(Messages.INVALID_FIELDS)
                .fields(fields)
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

    @ExceptionHandler(CarModelNotFoundException.class)
    public ResponseEntity<Object> handleCarModelNotFound(CarModelNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDescription errorDescription =ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title(exception.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<Object> handleCarNotFound(CarNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDescription errorDescription =ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title(exception.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

    @ExceptionHandler(ClientAlreadyExistException.class)
    public ResponseEntity<Object> handleClientAlreadyExist(ClientAlreadyExistException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDescription errorDescription =ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title(exception.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> handleClientNotFound(ClientNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDescription errorDescription =ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title(exception.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFound(EmployeeNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDescription errorDescription =ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title(exception.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

    @ExceptionHandler(SaleCannotBeMadeException.class)
    public ResponseEntity<Object> handleSaleCannotBeMade(SaleCannotBeMadeException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDescription errorDescription =ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title(exception.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

    @ExceptionHandler(SaleNotFoundException.class)
    public ResponseEntity<Object> handleSaleNotFound(SaleNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDescription errorDescription =ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title(exception.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

}
