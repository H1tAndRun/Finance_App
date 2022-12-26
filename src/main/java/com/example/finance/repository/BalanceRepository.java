package com.example.finance.repository;

import com.example.finance.entities.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance,Integer> {

    Optional<Balance> getBalanceByNumberBalance(String number);

}
