package com.example.finance.repository;

import com.example.finance.entities.Balance;
import com.example.finance.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Integer> {

    Optional<List<Operation>> getOperationByBalance_NumberBalance(String number);
}
