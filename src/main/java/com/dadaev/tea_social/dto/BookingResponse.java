package com.dadaev.tea_social.dto;

import java.time.LocalDateTime;

public record BookingResponse(
        String id,
        LocalDateTime confirmedAt
) {
}
