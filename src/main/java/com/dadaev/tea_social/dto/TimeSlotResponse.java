package com.dadaev.tea_social.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public record TimeSlotResponse(
        @JsonFormat(pattern = "HH:mm")
        LocalTime timeSlot
) {
}
