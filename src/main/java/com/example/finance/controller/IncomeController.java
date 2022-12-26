package com.example.finance.controller;

import com.example.finance.dto.IncomeDtoRq;
import com.example.finance.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class IncomeController {

    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/income")
    public ResponseEntity addMoneyByNumberBalance(@RequestBody IncomeDtoRq incomeDtoRq) {
        return new ResponseEntity<>(incomeService.addMoneyByNumberBalance(incomeDtoRq), HttpStatus.OK);
    }

    @GetMapping("/income/{number}")
    public ResponseEntity getIncomeByNumberBalance(@PathVariable String number) {
        return new ResponseEntity(incomeService.getIncomeByNumberBalance(number), HttpStatus.OK);
    }
}
