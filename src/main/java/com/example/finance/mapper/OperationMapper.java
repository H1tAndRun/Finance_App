package com.example.finance.mapper;

import com.example.finance.dto.OperationDtoRq;
import com.example.finance.dto.OperationDtoRs;
import com.example.finance.entities.Operation;
import com.example.finance.hendlers.NoSuchBalanceException;
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

    public Operation castFromDtoRq(OperationDtoRq operationDtoRq) {
        return new Operation(operationDtoRq.getNameOperation(),
                operationDtoRq.getSumOperation(),
                balanceRepository.getBalanceByNumberBalance(operationDtoRq.getNameBalance())
                        .orElseThrow(() -> new NoSuchBalanceException("Нет такого баланса")));
    }

    public OperationDtoRs castFromEntity(Operation operation) {
        return OperationDtoRs.builder()
                .nameOperation(operation.getNameOperation())
                .sumOperation(operation.getSum())
                .createdAt(operation.getCreatedAt())
                .build();
    }
}
