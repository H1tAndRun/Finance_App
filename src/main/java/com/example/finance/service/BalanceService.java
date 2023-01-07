package com.example.finance.service;

import com.example.finance.entities.Balance;

import java.math.BigDecimal;

public interface BalanceService {

     BigDecimal getMoneyBalanceByNumber(String number);
}
