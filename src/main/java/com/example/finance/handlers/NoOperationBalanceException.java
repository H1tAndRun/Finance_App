package com.example.finance.handlers;

public class NoOperationBalanceException extends RuntimeException{

    public NoOperationBalanceException(String message) {
        super(message);
    }
}
