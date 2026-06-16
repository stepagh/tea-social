package com.dadaev.tea_social.model;


import com.dadaev.tea_social.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "bookable_teas")
@Getter
@Setter
public class BookableTea {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal pricePerSession;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Currency currency;
    @ManyToOne()
    TeaHouse teaHouse;
}
