package com.dadaev.tea_social.dto;

import org.springframework.data.domain.Slice;

import java.util.List;


public record ReviewsPageResponse(
    List<ReviewPostResponse> items,
    int nextOffset,
    boolean hasMore
){
    public static ReviewsPageResponse from (Slice<ReviewPostResponse> slice) {
        List<ReviewPostResponse> posts = slice.getContent();
        int nextOffset = slice.hasNext() ? (slice.getNumber() + 1) * slice.getSize(): -1;
        return new ReviewsPageResponse(posts, nextOffset, slice.hasNext());
    }
}
