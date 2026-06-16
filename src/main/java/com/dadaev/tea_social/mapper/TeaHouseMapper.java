package com.dadaev.tea_social.mapper;

import com.dadaev.tea_social.dto.TeaHouseResponse;
import com.dadaev.tea_social.model.TeaHouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeaHouseMapper {

    TeaHouseResponse toResponse(TeaHouse teaHouse);
}
