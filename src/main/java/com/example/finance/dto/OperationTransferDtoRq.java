package com.example.finance.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OperationTransferDtoRq {

    private String nameFromBalance;

    private String nameToBalance;

    private BigDecimal sum;
}
