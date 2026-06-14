package com.dadaev.tea_social.dto;

public record UserProfileResponse(
    String id,
    String name,
    String bio,
    String avatarUrl,
    Integer teasDrunk,
    Integer teasRated,
    Integer favoritesCount) {}
