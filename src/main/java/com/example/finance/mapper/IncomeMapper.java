package com.example.finance.mapper;

import com.example.finance.dto.IncomeDtoRq;
import com.example.finance.dto.IncomeDtoRs;
import com.example.finance.entities.Balance;
import com.example.finance.entities.Income;
import com.example.finance.hendlers.NoSuchBalanceException;
import com.example.finance.repository.BalanceRepository;
import com.example.finance.service.BalanceService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IncomeMapper {

    private final BalanceService balanceService;


    // получить баланс по номеру
    // обновить баланс
    // добавить запись в таблицу пополнения

    @Autowired
    public IncomeMapper(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public Income castFromDtoRq(IncomeDtoRq incomeDtoRq){
        Balance balance = balanceService.getBalanceByNumber(incomeDtoRq.getNameBalance());
        return new Income(incomeDtoRq.getNameIncome(),incomeDtoRq.getSumIncome(),balance);
    }



    public IncomeDtoRs castFromEntity(Income income){
        return IncomeDtoRs.builder()
                .dateIncome(income.getDateIncome())
                .nameIncome(income.getNameIncome())
                .sumIncome(income.getSumIncome())
                .build();
    }
}
