package com.dadaev.tea_social.mapper;


import com.dadaev.tea_social.dto.*;

import com.dadaev.tea_social.model.*;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CommentMapper.class})
public interface ReviewMapper {

    @Mapping(target = "teaId", source = "tea.id")
    @Mapping(target = "teaName", source = "tea.name")
    @Mapping(target = "teaType", source = "tea.type")
    ReviewPostResponse toResponse(Review review);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "author", source = "author")
    @Mapping(target = "tags", ignore = true)
    Review toEntity(CreateReviewRequest request, Tea tea, User author);

    ReviewTagResponse toReviewTagResponse(ReviewTag reviewTag);

    ReviewTag toReviewTagEntity(CreateReviewRequest request);


}
