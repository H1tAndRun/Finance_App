package com.example.finance.mapper;

import com.example.finance.dto.OperationAddDtoRq;
import com.example.finance.dto.OperationTransferDtoRq;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OperationTransferMapper {

    public OperationAddDtoRq castFromTransferDtoToAddDtoNegate(OperationTransferDtoRq operationTransferDtoRq) {
        return OperationAddDtoRq.builder()
                .nameOperation("Перевод")
                .sumOperation(operationTransferDtoRq.getSum()
                        .negate())
                .numberBalance(operationTransferDtoRq.getNameFromBalance())
                .build();
    }

    public OperationAddDtoRq castFromTransferDtoToAddDto(OperationTransferDtoRq operationTransferDtoRq) {
        return OperationAddDtoRq.builder()
                .nameOperation("Перевод")
                .sumOperation(operationTransferDtoRq.getSum())
                .numberBalance(operationTransferDtoRq.getNameToBalance())
                .build();
    }
}
