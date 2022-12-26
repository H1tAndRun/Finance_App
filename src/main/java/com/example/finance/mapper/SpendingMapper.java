package com.example.finance.mapper;

import com.example.finance.dto.SpendingDtoRq;
import com.example.finance.dto.SpendingDtoRs;
import com.example.finance.entities.Balance;
import com.example.finance.entities.Spending;
import com.example.finance.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpendingMapper {

    private final BalanceService balanceService;

    @Autowired
    public SpendingMapper(BalanceService balanceRepository) {
        this.balanceService = balanceRepository;
    }

    public Spending castFromDtoRq(SpendingDtoRq spendingDtoRq) {
        Balance balance = balanceService.getBalanceByNumber(spendingDtoRq.getNameBalance());
        return new Spending(spendingDtoRq.getNameSpending(), spendingDtoRq.getSumSpending(), balance);
    }

    public SpendingDtoRs castFromEntity(Spending spending) {
        return SpendingDtoRs.builder().dateSpending(spending.getDateSpending())
                .sumSpending(spending.getSumSpending())
                .nameSpending(spending.getNameSpending())
                .build();
    }
}
