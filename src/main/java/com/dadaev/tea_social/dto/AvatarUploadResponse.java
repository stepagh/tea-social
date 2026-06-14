package com.dadaev.tea_social.dto;


import jakarta.validation.constraints.NotNull;

public record AvatarUploadResponse(
        String avatarUrl
) {
}
