package com.example.finance.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class SpendingDtoRq {

    private String nameSpending;

    private BigDecimal sumSpending;

    private String nameBalance;
}
