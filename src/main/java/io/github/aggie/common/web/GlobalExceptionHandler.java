package io.github.aggie.common.web;

import io.github.aggie.products.ProductNotFoundException;
import io.github.aggie.users.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionTransferObject> onException(Exception ex, Locale locale) {
        ex.printStackTrace();
        return createResponse(ex, INTERNAL_SERVER_ERROR, locale);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionTransferObject> onUserNotFoundException(UserNotFoundException ex, Locale locale) {
        return createResponse(ex, NOT_FOUND, locale);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionTransferObject> onUserNotFoundException(ProductNotFoundException ex, Locale locale) {
        return createResponse(ex, NOT_FOUND, locale);
    }

    private ResponseEntity<ExceptionTransferObject> createResponse(Exception ex, HttpStatus status, Locale locale) {
        var exceptionName = ex.getClass().getSimpleName();
        String description;
        try {
            description = messageSource.getMessage(ex.getClass().getSimpleName(), null, locale);
        } catch (NoSuchMessageException exception) {
            description = exceptionName;

        }
        ex.printStackTrace();
        return ResponseEntity.status(status).body(new ExceptionTransferObject(description));
    }
}
