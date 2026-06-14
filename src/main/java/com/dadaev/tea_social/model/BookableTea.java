package com.dadaev.tea_social.model;


import com.dadaev.tea_social.enums.Currency;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "bookable_teas")
public class BookableTea {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal pricePerSession;
    @Column(nullable = false)
    Currency currency;
}
