package com.example.finance.handlers;

import com.example.finance.exception.NoQuoterAGivenDay;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CursHandler {

    @ExceptionHandler(NoQuoterAGivenDay.class)
    public ResponseEntity handle(NoQuoterAGivenDay exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
