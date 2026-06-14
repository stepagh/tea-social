package com.dadaev.tea_social.model;

import com.dadaev.tea_social.enums.TeaType;
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
    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TeaType type;
}
