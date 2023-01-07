package com.example.finance.service;

import com.example.finance.dto.OperationDtoRq;
import com.example.finance.dto.OperationDtoRs;
import com.example.finance.entities.Operation;

import java.util.List;

public interface OperationService {

    String addOperationByBalance(OperationDtoRq operationDtoRq);

    List<OperationDtoRs> getOperationByNumberBalance(String number);
}
