package com.dadaev.tea_social.dto;

public record AuthResponse(
    String token,
    UserDTO user
) {}
