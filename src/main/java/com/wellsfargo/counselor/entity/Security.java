package com.wellsfargo.counselor.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "securities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Security {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long security_id;
    private String name;
    private String category;
    private BigDecimal purchasePrice;
    private LocalDate purchaseDate;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

}
