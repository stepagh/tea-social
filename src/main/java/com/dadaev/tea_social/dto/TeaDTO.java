package com.dadaev.tea_social.dto;

import com.dadaev.tea_social.enums.TeaType;

public record TeaDTO(
        String id,
        String name,
        TeaType type
) {}
