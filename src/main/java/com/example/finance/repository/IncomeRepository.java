package com.example.finance.repository;

import com.example.finance.entities.Balance;
import com.example.finance.entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income,Integer> {

}
