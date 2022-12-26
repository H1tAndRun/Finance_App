package com.example.finance.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class SpendingDtoRs {
    private String nameSpending;

    private BigDecimal sumSpending;

    private LocalDate dateSpending;
}
