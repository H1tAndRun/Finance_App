package com.example.finance.service;

import com.example.finance.entities.Balance;
import com.example.finance.hendlers.NoSuchBalanceException;
import com.example.finance.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;


    @Autowired
    public BalanceServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public BigDecimal getMoneyBalanceByNumber(String number) {
        Balance balance = balanceRepository.getBalanceByNumberBalance(number)
                .orElseThrow(() -> new NoSuchBalanceException("Нет такого баланса"));
        return balance.getMoney();
    }

}
