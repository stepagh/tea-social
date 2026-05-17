package com.dadaev.tea_social.mapper;

import com.dadaev.tea_social.dto.AuthorDTO;
import com.dadaev.tea_social.model.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AuthorDTO toDto(User user);

    @InheritConfiguration(name = "toDto")
    User toEntity(AuthorDTO dto);
}