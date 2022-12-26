package com.example.finance.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class IncomeDtoRs {
    private String nameIncome;

    private BigDecimal sumIncome;

    private LocalDate dateIncome;
}
