package com.dadaev.tea_social.mapper;

import com.dadaev.tea_social.dto.CommentDTO;
import com.dadaev.tea_social.model.Comment;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDTO toDTO(Comment comment);

    @InheritConfiguration(name = "toDto")
    Comment toEnitty(CommentDTO dto);
}