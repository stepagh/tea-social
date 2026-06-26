package com.dadaev.tea_social.dto;

import com.dadaev.tea_social.enums.Currency;

import java.math.BigDecimal;

public record BookableTeaResponse(
        Long id,
        String teaHouseId,
        String name,
        BigDecimal pricePerSession,
        Currency currency) {
}
