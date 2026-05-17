package com.dadaev.tea_social.dto;

import lombok.Data;

import java.util.List;


public record FeedPageDTO(
    List<ReviewPostDTO> items,
    int nextOffset,
    boolean hasMore
){}
