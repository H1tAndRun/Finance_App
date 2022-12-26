package com.example.finance.service;

import com.example.finance.dto.IncomeDtoRs;
import com.example.finance.dto.SpendingDtoRq;
import com.example.finance.dto.SpendingDtoRs;
import com.example.finance.entities.Balance;
import com.example.finance.entities.Spending;
import com.example.finance.hendlers.NoCorrectIncome;
import com.example.finance.hendlers.NoCorrectSpending;
import com.example.finance.mapper.SpendingMapper;
import com.example.finance.repository.SpendingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpendingService {

    private final SpendingRepository spendingRepository;

    private final SpendingMapper spendingMapper;

    private final BalanceService balanceService;

    public String addSpendingByNumberBalance(SpendingDtoRq spendingDtoRq) {
        if (spendingDtoRq.getSumSpending().compareTo(new BigDecimal("0")) >= 0) {
            throw new NoCorrectSpending("Расходы не могут быть больше 0");
        }
        Spending spending = spendingMapper.castFromDtoRq(spendingDtoRq);
        Balance balance = spending.getBalance();
        balance.setMoney(balance.getMoney().add(spending.getSumSpending()));
        spendingRepository.save(spending);
        return "Товар куплен";
    }

    public List<SpendingDtoRs> getSpendingByNumberBalance(String number) {
        Balance balance = balanceService.getBalanceByNumber(number);
        List<Spending> spendings = balance.getSpendings();
        if (spendings == null || spendings.isEmpty()) {
            throw new NoCorrectIncome("Нет трат по указанному балансу");
        }
        List<SpendingDtoRs> spendingDtoRs = spendings.stream()
                .map(spendingMapper::castFromEntity)
                .collect(Collectors.toList());
        return spendingDtoRs;
    }
}
