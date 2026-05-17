package com.dadaev.tea_social.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "teas")
public class Tea {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    String year;
    @Column(nullable = false)
    String origin;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    TeaType type;
}
