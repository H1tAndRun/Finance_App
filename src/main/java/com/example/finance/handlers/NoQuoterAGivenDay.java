package com.example.finance.handlers;

public class NoQuoterAGivenDay extends RuntimeException{
    public NoQuoterAGivenDay(String message) {
        super(message);
    }
}
