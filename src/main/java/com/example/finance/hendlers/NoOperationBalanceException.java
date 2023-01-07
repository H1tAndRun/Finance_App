package com.example.finance.hendlers;

public class NoOperationBalanceException extends RuntimeException{

    public NoOperationBalanceException(String message) {
        super(message);
    }
}
