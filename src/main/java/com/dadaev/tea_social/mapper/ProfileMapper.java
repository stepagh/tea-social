package com.dadaev.tea_social.mapper;

import com.dadaev.tea_social.dto.AuthorDTO;
import com.dadaev.tea_social.dto.UserProfileResponse;
import com.dadaev.tea_social.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "name", source = "user.username")
    UserProfileResponse toResponse(UserProfile userProfile);


}
