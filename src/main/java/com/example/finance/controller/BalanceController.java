package com.example.finance.controller;

import com.example.finance.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/balance/{number}")
    public ResponseEntity getMoneyBalanceByNumber(@PathVariable String number) {
        return new ResponseEntity<>(balanceService.getBalanceByNumber(number).getMoney(),HttpStatus.OK);
    }
}
