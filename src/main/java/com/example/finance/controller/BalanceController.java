package com.example.finance.controller;

import com.example.finance.annotation.ToLog;

import com.example.finance.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BalanceController {

    private final BalanceService balanceService;

    @ToLog
    @GetMapping("/balance/{number}")
    public ResponseEntity<BigDecimal> getMoneyBalanceByNumber(@PathVariable String number) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(balanceService.getMoneyBalanceByNumber(number));
    }
}
