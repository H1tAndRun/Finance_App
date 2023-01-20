package com.example.finance.repository;

import com.example.finance.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

    List<Currency> getCurrenciesByCurs_Date(LocalDate date);
}
