package com.example.finance.entities;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "operations")
@EqualsAndHashCode(exclude = "operations")
public class Balance {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    @NonNull
    private String numberBalance;

    @Column(nullable = false)
    @NonNull
    private BigDecimal money;

    @OneToMany(mappedBy = "balance")
    private List<Operation> operations;
}
