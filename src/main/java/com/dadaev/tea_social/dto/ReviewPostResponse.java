package com.dadaev.tea_social.dto;

import com.dadaev.tea_social.enums.TeaType;

import java.time.LocalDate;
import java.util.List;

public record ReviewPostResponse(
     Long id,
     String reviewText,
     int rating,
     LocalDate createdAt,
     String imageUrl,
     AuthorDto author,
     long teaId,
     String teaName,
     TeaType teaType,
     ReviewTagResponse tags,
     List<CommentResponse> comments
) {}

