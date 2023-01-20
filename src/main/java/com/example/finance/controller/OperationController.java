package com.example.finance.controller;

import com.example.finance.dto.OperationDtoRq;
import com.example.finance.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OperationController {

    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/operation/{number}")
    public ResponseEntity getOperationByNumberBalance(@PathVariable String number) {
        return new ResponseEntity(operationService.getOperationByNumberBalance(number), HttpStatus.OK);
    }

    @PostMapping("/operation")
    public ResponseEntity addOperationByBalance(@RequestBody OperationDtoRq operationDtoRq) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(operationService.addOperationByBalance(operationDtoRq));
    }
}
