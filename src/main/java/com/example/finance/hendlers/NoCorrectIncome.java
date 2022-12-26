package com.example.finance.hendlers;

public class NoCorrectIncome extends RuntimeException{

    public NoCorrectIncome (String message) {
        super(message);
    }
}
