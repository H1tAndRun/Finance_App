package com.example.finance.exception;

public class NoQuoterAGivenDay extends RuntimeException{
    public NoQuoterAGivenDay(String message) {
        super(message);
    }
}
