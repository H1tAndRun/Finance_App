package com.example.finance.hendlers;

public class NoCorrectSpending extends RuntimeException{
    public NoCorrectSpending(String message) {
        super(message);
    }
}
