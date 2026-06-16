package com.dadaev.tea_social.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record BookingRequest(
         Long teaHouseId,
         Long teaId,
         LocalDate date,
         LocalTime timeSlot) {}
