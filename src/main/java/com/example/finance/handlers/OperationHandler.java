package com.example.finance.handlers;

import com.example.finance.exception.NoOperationBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OperationHandler {

    @ExceptionHandler(NoOperationBalanceException.class)
    public ResponseEntity handle(NoOperationBalanceException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
