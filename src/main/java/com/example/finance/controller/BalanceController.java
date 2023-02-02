package com.example.finance.controller;

import com.example.finance.annotation.ToLog;
import com.example.finance.dto.OperationAddDtoRq;
import com.example.finance.dto.OperationAddDtoRs;
import com.example.finance.dto.OperationTransferDtoRq;
import com.example.finance.service.BalanceService;
import com.example.finance.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;

    private final OperationService operationService;

    @ToLog
    @GetMapping("/{number}")
    public ResponseEntity<BigDecimal> getMoneyBalanceByNumber(@PathVariable String number) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(balanceService.getMoneyBalanceByNumber(number));
    }


    @GetMapping("/operation/{number}")
    public ResponseEntity<List<OperationAddDtoRs>> getOperationByNumberBalance(@PathVariable String number) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(operationService.getOperationByNumberBalance(number));
    }

    @PostMapping("/operation")
    public ResponseEntity<BigDecimal> addOperationByBalance(@RequestBody OperationAddDtoRq operationAddDtoRq) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(operationService.addOperationByBalance(operationAddDtoRq));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFromBalanceToBalance(
            @RequestBody OperationTransferDtoRq operationTransferDtoRq) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(operationService.transferFromBalanceToBalance(operationTransferDtoRq));
    }
}
