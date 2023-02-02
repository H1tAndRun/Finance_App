package com.example.finance.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class OperationAddDtoRq {

    private String nameOperation;

    private BigDecimal sumOperation;

    private String numberBalance;
}
