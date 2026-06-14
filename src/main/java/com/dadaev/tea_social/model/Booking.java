package com.dadaev.tea_social.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    TeaHouse teaHouse;

    @ManyToOne
    BookableTea tea;
    LocalDateTime time;
    LocalTime timeSlot;

}
