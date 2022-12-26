package com.example.finance.entities;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "balance")
@EqualsAndHashCode(exclude = "balance" )
public class Income {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NonNull
    private String nameIncome;

    @Column(nullable = false)
    @NonNull
    private BigDecimal sumIncome;

    @Column(nullable = false)
    private LocalDate dateIncome=LocalDate.now();  //подрихтовать

    @ManyToOne
    @JoinColumn(nullable = false,name = "balance_id")
    @NonNull
    private Balance balance;
}
