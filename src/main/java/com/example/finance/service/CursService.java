package com.example.finance.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface CursService {

    Map<String,BigDecimal> convertRublesToCurrency(BigDecimal sum, LocalDate date);

}
