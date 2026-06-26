package com.dadaev.tea_social.dto;

import com.dadaev.tea_social.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record BookingDto(
        Long id,
        LocalDateTime confirmedAt,
        Long teaHouseId,
        String teaHouseName,
        Long teaId,
        String teaName,
        LocalDate date,
        LocalTime timeSlot,
        BigDecimal pricePerSession,
        Currency currency) {}
