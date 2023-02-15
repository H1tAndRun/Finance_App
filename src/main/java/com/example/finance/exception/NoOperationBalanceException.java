package com.example.finance.exception;

public class NoOperationBalanceException extends RuntimeException{

    public NoOperationBalanceException(String message) {
        super(message);
    }
}
