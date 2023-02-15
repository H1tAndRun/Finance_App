package com.example.finance.controller;

import com.example.finance.service.CursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class CursController {

    private final CursService cursService;

    @Autowired
    public CursController(CursService cursService) {
        this.cursService = cursService;
    }

    @GetMapping("/curs")
    public ResponseEntity getValueIsRubles(@RequestParam BigDecimal sum,
                                           @RequestParam LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursService.convertRublesToCurrency(sum, date));
    }
}
