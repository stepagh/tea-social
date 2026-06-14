package com.dadaev.tea_social.mapper;

import com.dadaev.tea_social.dto.AuthorDTO;
import com.dadaev.tea_social.model.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "avatarUrl", source = "userProfile.avatarUrl")
    AuthorDTO toDto(User user);

    @InheritInverseConfiguration(name = "toDto")
    User toEntity(AuthorDTO dto);

}