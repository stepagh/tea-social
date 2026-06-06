package com.dadaev.tea_social.mapper;

import com.dadaev.tea_social.dto.CommentDTO;
import com.dadaev.tea_social.model.Comment;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDTO toDto(Comment comment);

    @InheritInverseConfiguration(name = "toDto")
    Comment toEntity(CommentDTO dto);
}