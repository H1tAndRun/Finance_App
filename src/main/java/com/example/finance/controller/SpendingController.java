package com.example.finance.controller;

import com.example.finance.dto.SpendingDtoRq;
import com.example.finance.service.SpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpendingController {

    private final SpendingService spendingService;

    @Autowired
    public SpendingController(SpendingService spendingService) {
        this.spendingService = spendingService;
    }

    @PostMapping("/spending")
    public ResponseEntity addSpendingByNumberBalance(@RequestBody SpendingDtoRq spendingDtoRq) {
        return new ResponseEntity(spendingService.addSpendingByNumberBalance(spendingDtoRq), HttpStatus.OK);
    }

    @GetMapping("/spending/{number}")
    public ResponseEntity getSpendingByNumberBalance(@PathVariable String number) {
        return new ResponseEntity(spendingService.getSpendingByNumberBalance(number), HttpStatus.OK);
    }
}
