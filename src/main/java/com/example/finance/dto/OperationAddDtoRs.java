package com.example.finance.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OperationAddDtoRs {

    private String nameOperation;

    private BigDecimal sumOperation;

    private LocalDateTime createdAt;
}
