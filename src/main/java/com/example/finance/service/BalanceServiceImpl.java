package com.example.finance.service;

import com.example.finance.entities.Balance;
import com.example.finance.handlers.NoSuchBalanceException;
import com.example.finance.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CacheConfig(cacheNames = "balance")
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;


    @Override
    @Cacheable(key = "#number")
    public BigDecimal getMoneyBalanceByNumber(String number) {
        Balance balance = balanceRepository.getBalanceByNumberBalance(number).
                orElseThrow(() -> new NoSuchBalanceException("Нет такого баланса"));
        return balance.getMoney();
    }
}
