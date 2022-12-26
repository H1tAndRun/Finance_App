package com.example.finance.hendlers;

public class NoSuchBalanceException extends RuntimeException {

    public NoSuchBalanceException(String message) {
        super(message);
    }
}
