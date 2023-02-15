package com.example.finance.service.impl;

import com.example.finance.dto.OperationAddDtoRq;
import com.example.finance.dto.OperationAddDtoRs;
import com.example.finance.dto.OperationTransferDtoRq;
import com.example.finance.entities.Balance;
import com.example.finance.entities.Operation;
import com.example.finance.exception.NoOperationBalanceException;
import com.example.finance.exception.NoSuchBalanceException;
import com.example.finance.mapper.OperationMapper;
import com.example.finance.mapper.OperationTransferMapper;
import com.example.finance.repository.OperationRepository;
import com.example.finance.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperationServiceImpl implements OperationService {

    private final OperationMapper operationMapper;

    private final OperationRepository operationRepository;

    private final OperationTransferMapper operationTransferMapper;

    @Override
    @Transactional
    @CachePut(value = "balance", key = "#operationAddDtoRq.numberBalance")
    public BigDecimal addOperationByBalance(OperationAddDtoRq operationAddDtoRq) {
        Operation operation = operationMapper.castFromDtoRq(operationAddDtoRq);
        Balance balance = operation.getBalance();
        if (balance.getMoney().add(operation.getSum()).compareTo(new BigDecimal("0")) < 0) {
            throw new NoSuchBalanceException("Не хватает денег на счете");
        }
        balance.setMoney(balance.getMoney().add(operation.getSum()));
        operationRepository.save(operation);
        return balance.getMoney();
    }


    @Override
    @Cacheable(value = "operation",key = "#number")
    public List<OperationAddDtoRs> getOperationByNumberBalance(String number) {
        List<Operation> operations = operationRepository.getOperationByBalance_NumberBalance(number)
                .orElseThrow(() -> new NoOperationBalanceException("Нет операций по указанному счету"));
        if (operations.isEmpty()) {
            throw new NoOperationBalanceException("Нет операций по указанному счету");
        }
        return operations.stream().map(operationMapper::castFromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    @CacheEvict(value = "balance", allEntries = true)
    public String transferFromBalanceToBalance(OperationTransferDtoRq operationTransferDtoRq) {
        if (operationTransferDtoRq.getSum().compareTo(new BigDecimal("0")) < 0) {
            throw new NoSuchBalanceException("Нельзя перевести отрицательную сумму");
        }
        addOperationByBalance(operationTransferMapper.castFromTransferDtoToAddDtoNegate(operationTransferDtoRq));
        addOperationByBalance(operationTransferMapper.castFromTransferDtoToAddDto(operationTransferDtoRq));
        return "Операция прошла успешно";
    }
}
