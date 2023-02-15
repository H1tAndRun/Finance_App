package com.example.finance.handlers;

import com.example.finance.exception.NoSuchBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BalanceHandler {

    @ExceptionHandler(NoSuchBalanceException.class)
    public ResponseEntity handle(NoSuchBalanceException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
