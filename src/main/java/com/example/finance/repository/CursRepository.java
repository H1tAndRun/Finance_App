package com.example.finance.repository;

import com.example.finance.entities.Curs;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface CursRepository extends JpaRepository<Curs,Integer> {

    Optional<Curs> getCursByDate(LocalDate localDate);

}
