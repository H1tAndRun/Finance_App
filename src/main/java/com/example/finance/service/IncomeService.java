package com.example.finance.service;

import com.example.finance.dto.IncomeDtoRq;
import com.example.finance.dto.IncomeDtoRs;
import com.example.finance.entities.Balance;
import com.example.finance.entities.Income;
import com.example.finance.hendlers.NoCorrectIncome;
import com.example.finance.hendlers.NoSuchBalanceException;
import com.example.finance.mapper.IncomeMapper;
import com.example.finance.repository.BalanceRepository;
import com.example.finance.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IncomeService {

    private final IncomeRepository incomeRepository;

    private final IncomeMapper incomeMapper;

    private final BalanceService balanceService;

    public String addMoneyByNumberBalance(IncomeDtoRq incomeDtoRq) {
        if (incomeDtoRq.getSumIncome().compareTo(new BigDecimal("0")) <= 0) {
            throw new NoCorrectIncome("Пополнение не может быть меньше или равно 0");
        }
        Balance balance = balanceService.getBalanceByNumber(incomeDtoRq.getNameBalance());
        Income income = incomeMapper.castFromDtoRq(incomeDtoRq);
        balance.setMoney(balance.getMoney().add(income.getSumIncome()));
        incomeRepository.save(income);
        return "Баланс пополнился";
    }

    public List<IncomeDtoRs> getIncomeByNumberBalance(String number) {
        Balance balance = balanceService.getBalanceByNumber(number);
        List<Income> incomes = balance.getIncomes();
        if (incomes == null || incomes.isEmpty()) {
            throw new NoCorrectIncome("Нет трат по указанному счету");
        }
        List<IncomeDtoRs> incomesDtoRs = incomes.stream()
                .map(incomeMapper::castFromEntity)
                .collect(Collectors.toList());
        return incomesDtoRs;
    }
}
