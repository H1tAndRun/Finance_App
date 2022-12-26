package com.example.finance.repository;

import com.example.finance.entities.Spending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingRepository extends JpaRepository<Spending,Integer> {
}
