package com.dadaev.tea_social.dto;

import java.time.LocalDateTime;

public record CommentDTO(
    String id,
    String authorName,
    String text,
    LocalDateTime createdAt) {}
