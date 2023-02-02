package com.example.finance.service;

import com.example.finance.dto.OperationAddDtoRq;
import com.example.finance.dto.OperationAddDtoRs;
import com.example.finance.dto.OperationTransferDtoRq;

import java.math.BigDecimal;
import java.util.List;

public interface OperationService {

    BigDecimal addOperationByBalance(OperationAddDtoRq operationAddDtoRq);

    List<OperationAddDtoRs> getOperationByNumberBalance(String number);

    String transferFromBalanceToBalance(OperationTransferDtoRq operationTransferDtoRq);
}
