package com.example.finance;

import com.example.finance.entities.Balance;
import com.example.finance.mapper.OperationTransferMapper;
import com.example.finance.repository.BalanceRepository;
import com.example.finance.service.BalanceService;
import com.example.finance.service.OperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class BalanceServiceTest {

    @Autowired
    private BalanceService balanceService;

    @MockBean
    private BalanceRepository balanceRepository;

    @MockBean
    private OperationTransferMapper operationTransferMapper;

    @MockBean
    private OperationService operationService;

    @Test
    public void getMoneyBalanceByNumberTest() {
        Mockito.when(balanceRepository.getBalanceByNumberBalance(Mockito.any()))
                .thenReturn(Optional.of(new Balance("1234", new BigDecimal("10000"))));
        Assertions.assertEquals(new BigDecimal("10000"), balanceService.getMoneyBalanceByNumber("12345"));
    }
}
