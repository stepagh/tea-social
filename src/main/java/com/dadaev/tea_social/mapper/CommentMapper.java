package com.dadaev.tea_social.mapper;

import com.dadaev.tea_social.dto.CommentResponse;
import com.dadaev.tea_social.dto.CreateCommentRequest;
import com.dadaev.tea_social.model.Comment;
import com.dadaev.tea_social.model.Review;
import com.dadaev.tea_social.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentResponse toResponse(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "request.text")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "review", source = "review")
    Comment toCommentEntity(CreateCommentRequest request, Review review, User author);
}