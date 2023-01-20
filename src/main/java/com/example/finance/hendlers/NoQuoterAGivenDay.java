package com.example.finance.hendlers;

public class NoQuoterAGivenDay extends RuntimeException{
    public NoQuoterAGivenDay(String message) {
        super(message);
    }
}
