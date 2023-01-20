package com.example.finance.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OperationDtoRq {

    private String nameOperation;

    private BigDecimal sumOperation;

    private String nameBalance;
}
