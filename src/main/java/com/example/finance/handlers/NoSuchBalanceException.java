package com.example.finance.handlers;

public class NoSuchBalanceException extends RuntimeException {

    public NoSuchBalanceException(String message) {
        super(message);
    }
}
