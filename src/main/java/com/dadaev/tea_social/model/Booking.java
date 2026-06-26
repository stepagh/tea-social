package com.dadaev.tea_social.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    TeaHouse teaHouse;
    @ManyToOne(fetch = FetchType.LAZY)
    BookableTea tea;
    @ManyToOne(fetch = FetchType.LAZY)
    UserProfile userProfile;
    LocalDate date;
    LocalTime timeSlot;
    LocalDateTime confirmedAt;

    @PrePersist
    private void onCreate() {
        this.confirmedAt = LocalDateTime.now();
    }
}
