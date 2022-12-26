package com.example.finance.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "balance")
@EqualsAndHashCode(exclude = "balance")
public class Spending {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NonNull
    private String nameSpending;

    @Column(nullable = false)
    @NonNull
    private BigDecimal sumSpending;

    @Column(nullable = false)
    private LocalDate dateSpending=LocalDate.now(); // подрихтовать

    @ManyToOne
    @JoinColumn(nullable = false,name = "balance_id")
    @NonNull
    private Balance balance;
}
