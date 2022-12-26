package com.example.finance.service;

import com.example.finance.entities.Balance;
import com.example.finance.hendlers.NoSuchBalanceException;
import com.example.finance.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;

    @Autowired
    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public Balance getBalanceByNumber(String number) {
        Balance balance = balanceRepository.getBalanceByNumberBalance(number)
                .orElseThrow(()-> new NoSuchBalanceException("Нет такого баланса"));
        return balance;
    }
}
