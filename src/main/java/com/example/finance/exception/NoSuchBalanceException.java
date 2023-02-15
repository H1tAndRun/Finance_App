package com.example.finance.exception;

public class NoSuchBalanceException extends RuntimeException {

    public NoSuchBalanceException(String message) {
        super(message);
    }
}
