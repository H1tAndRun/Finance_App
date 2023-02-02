package com.example.finance;

import com.example.finance.dto.OperationAddDtoRq;
import com.example.finance.dto.OperationAddDtoRs;
import com.example.finance.entities.Balance;
import com.example.finance.entities.Operation;
import com.example.finance.mapper.OperationMapper;
import com.example.finance.repository.OperationRepository;
import com.example.finance.service.OperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OperationServiceTest {

    @Autowired
    private OperationService operationService;

    @MockBean
    private OperationMapper operationMapper;

    @MockBean
    private OperationRepository operationRepository;


    @Test
    @DisplayName("Проверка метода добавления операции")
    public void addOperationByBalanceTest() {
        Operation operation = Operation.builder().nameOperation("перевод")
                .sum(new BigDecimal("10000"))
                .balance(new Balance("1234", new BigDecimal("10000")))
                .build();
        OperationAddDtoRq operationAddDtoRq = OperationAddDtoRq.builder()
                .nameOperation("перевод")
                .sumOperation(new BigDecimal("10000"))
                .build();
        Mockito.when(operationMapper.castFromDtoRq(Mockito.any()))
                .thenReturn(operation);
        Mockito.when(operationRepository.save(Mockito.any()))
                .thenReturn(operation);
        Assertions.assertEquals("Баланс изменен", operationService.addOperationByBalance(operationAddDtoRq));
    }

    @Test
    @DisplayName("Проверка метода получения операций по номеру баланса")
    public void getOperationByNumberBalanceTest() {
        Mockito.when(operationRepository.getOperationByBalance_NumberBalance(Mockito.any()))
                .thenReturn(Optional.of(List.of(Operation
                        .builder()
                        .nameOperation("перевод")
                        .sum(new BigDecimal("10000"))
                        .balance(new Balance("1234", new BigDecimal("10000")))
                        .build())));
        Mockito.when(operationMapper.castFromEntity(Mockito.any()))
                .thenReturn(OperationAddDtoRs.
                        builder()
                        .createdAt(LocalDate.of(2000, 1, 1).atStartOfDay())
                        .nameOperation("перевод")
                        .sumOperation(new BigDecimal("10000"))
                        .build());
        List<OperationAddDtoRs> actual = List.of(OperationAddDtoRs.builder()
                .createdAt(LocalDate.of(2000, 1, 1).atStartOfDay())
                .nameOperation("перевод")
                .sumOperation(new BigDecimal("10000"))
                .build());
        Assertions.assertEquals(operationService.getOperationByNumberBalance("1234"), actual);
    }
}
