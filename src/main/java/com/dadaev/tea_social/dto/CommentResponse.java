package com.dadaev.tea_social.dto;

import java.time.LocalDateTime;

public record CommentResponse(
    Long id,
    String authorName,
    String text,
    LocalDateTime createdAt){}
