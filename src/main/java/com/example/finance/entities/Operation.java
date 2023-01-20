package com.example.finance.entities;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "balance")
@EqualsAndHashCode(exclude = "balance")
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    @NonNull
    private String nameOperation;

    @Column(nullable = false)
    @NonNull
    private BigDecimal sum;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "balance_id",nullable = false)
    @NonNull
    private Balance balance;
}
