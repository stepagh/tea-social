package com.dadaev.tea_social.mapper;

import com.dadaev.tea_social.dto.BookingRequest;
import com.dadaev.tea_social.dto.BookingResponse;
import com.dadaev.tea_social.model.BookableTea;
import com.dadaev.tea_social.model.Booking;
import com.dadaev.tea_social.model.TeaHouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "confirmedAt", ignore = true)
    @Mapping(target = "teaHouse", source = "teaHouse")
    @Mapping(target = "tea", source = "bookableTea")
    Booking toEntity(BookingRequest bookingRequest, TeaHouse teaHouse, BookableTea bookableTea);

    BookingResponse toResponse(Booking booking);
}
