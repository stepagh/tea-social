package com.dadaev.tea_social.mapper;

import com.dadaev.tea_social.dto.BookableTeaResponse;
import com.dadaev.tea_social.model.BookableTea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookableTeaMapper {
    @Mapping(target = "teaHouseId", source = "teaHouse.id")
    BookableTeaResponse toResponse(BookableTea bookableTea);
}
