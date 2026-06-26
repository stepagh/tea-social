package com.dadaev.tea_social.dto;

import com.dadaev.tea_social.enums.TeaType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record CreateReviewRequest(
        Long teaId,
        String customTeaName,
        TeaType teaType,
        int year,
        String origin,
        @NotBlank
        String reviewText,
        @NotNull
        @Min(value = 1)
        @Max(value = 5)
        Integer rating,
        MultipartFile image

) {
}
