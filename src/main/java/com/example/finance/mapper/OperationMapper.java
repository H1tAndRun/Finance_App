package com.example.finance.mapper;

import com.example.finance.dto.OperationAddDtoRq;
import com.example.finance.dto.OperationAddDtoRs;
import com.example.finance.entities.Operation;
import com.example.finance.handlers.NoSuchBalanceException;
import com.example.finance.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper {

    private final BalanceRepository balanceRepository;

    @Autowired
    public OperationMapper(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public Operation castFromDtoRq(OperationAddDtoRq operationAddDtoRq) {
        return new Operation(operationAddDtoRq.getNameOperation(),
                operationAddDtoRq.getSumOperation(),
                balanceRepository.getBalanceByNumberBalance(operationAddDtoRq.getNumberBalance())
                        .orElseThrow(() -> new NoSuchBalanceException("Нет такого баланса")));
    }

    public OperationAddDtoRs castFromEntity(Operation operation) {
        return OperationAddDtoRs.builder()
                .nameOperation(operation.getNameOperation())
                .sumOperation(operation.getSum())
                .createdAt(operation.getCreatedAt())
                .build();
    }
}
