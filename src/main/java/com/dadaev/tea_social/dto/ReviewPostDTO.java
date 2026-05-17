package com.dadaev.tea_social.dto;

import com.dadaev.tea_social.model.Comment;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public record ReviewPostDTO(
     String id,
     String reviewText,
     int rating,
     LocalDate createdAt,
     String imageUrl,
     AuthorDTO author,
     long teaId,
     String teaName,
     List<ReviewTagDTO> tags,
     List<CommentDTO> comments
) {}

