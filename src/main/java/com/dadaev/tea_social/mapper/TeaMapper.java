package com.dadaev.tea_social.mapper;

import com.dadaev.tea_social.dto.CreateReviewRequest;
import com.dadaev.tea_social.dto.TeaDto;
import com.dadaev.tea_social.model.Tea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeaMapper {
    TeaDto toDto(Tea entity);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "customTeaName")
    @Mapping(target = "type", source = "teaType")
    Tea toEntity(CreateReviewRequest request);
}
