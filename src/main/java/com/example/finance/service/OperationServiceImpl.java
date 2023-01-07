package com.example.finance.service;

import com.example.finance.dto.OperationDtoRq;
import com.example.finance.dto.OperationDtoRs;
import com.example.finance.entities.Balance;
import com.example.finance.entities.Operation;
import com.example.finance.hendlers.NoOperationBalanceException;
import com.example.finance.hendlers.NoSuchBalanceException;
import com.example.finance.mapper.OperationMapper;
import com.example.finance.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperationServiceImpl implements OperationService {

    private final OperationMapper operationMapper;

    private final OperationRepository operationRepository;

    @Override
    public String addOperationByBalance (OperationDtoRq operationDtoRq) {
        Operation operation = operationMapper.castFromDtoRq(operationDtoRq);
        Balance balance = operation.getBalance();
        if (balance.getMoney().add(operation.getSum()).compareTo(new BigDecimal("0")) < 0) {
            throw new NoSuchBalanceException("Не хватает денег на счете");
        }
        balance.setMoney(balance.getMoney().add(operation.getSum()));
        operationRepository.save(operation);
        return "Баланс изменен";
    }

    @Override
    public List<OperationDtoRs> getOperationByNumberBalance(String number) {
        List<Operation> operations = operationRepository.getOperationByBalance_NumberBalance(number)
                .orElseThrow(() -> new NoOperationBalanceException("Нет операций по указанному счету"));
        if (operations.isEmpty()) {
            throw new NoOperationBalanceException("Нет операций по указанному счету");
        }
        return operations.stream().map(operationMapper::castFromEntity).collect(Collectors.toList());
    }
}
