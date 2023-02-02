package com.example.finance.service;

import com.example.finance.dto.OperationTransferDtoRq;

import java.math.BigDecimal;

public interface BalanceService {

    BigDecimal getMoneyBalanceByNumber(String number);
}
