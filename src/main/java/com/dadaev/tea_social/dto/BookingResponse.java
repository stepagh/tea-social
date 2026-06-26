package com.dadaev.tea_social.dto;

import java.time.LocalDateTime;

public record BookingResponse(
        Long id,
        LocalDateTime confirmedAt
) {
}
