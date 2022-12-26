package com.example.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class IncomeDtoRq {

    private String nameIncome;

    private String nameBalance;

    private BigDecimal sumIncome;
}
