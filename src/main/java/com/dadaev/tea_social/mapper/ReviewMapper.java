package com.dadaev.tea_social.mapper;


import com.dadaev.tea_social.dto.ReviewPostDTO;
import com.dadaev.tea_social.dto.ReviewTagDTO;

import com.dadaev.tea_social.model.Review;
import com.dadaev.tea_social.model.ReviewTag;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CommentMapper.class})
public interface ReviewMapper {
    @Mapping(target = "teaId", source = "tea.id")
    @Mapping(target = "teaName", source = "tea.name")
    ReviewPostDTO toDto(Review review);

    @InheritConfiguration(name = "toDto")
    Review toEntity(ReviewPostDTO dto);

    ReviewTagDTO toReviewTagDto(ReviewTag reviewTag);

}
