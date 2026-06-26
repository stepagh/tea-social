package com.dadaev.tea_social.mapper;

import com.dadaev.tea_social.dto.BookingDto;
import com.dadaev.tea_social.dto.BookingRequest;
import com.dadaev.tea_social.dto.BookingResponse;
import com.dadaev.tea_social.model.BookableTea;
import com.dadaev.tea_social.model.Booking;
import com.dadaev.tea_social.model.TeaHouse;
import com.dadaev.tea_social.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "confirmedAt", ignore = true)
    @Mapping(target = "teaHouse", source = "teaHouse")
    @Mapping(target = "tea", source = "bookableTea")
    @Mapping(target = "userProfile", source = "userProfile")
    Booking toEntity(BookingRequest bookingRequest, TeaHouse teaHouse, BookableTea bookableTea, UserProfile userProfile);

    BookingResponse toResponse(Booking booking);

    @Mapping(target = "teaHouseId", source = "teaHouse.id")
    @Mapping(target = "teaHouseName", source = "teaHouse.name")
    @Mapping(target = "teaId", source = "tea.id")
    @Mapping(target = "teaName", source = "tea.name")
    @Mapping(target = "pricePerSession", source = "tea.pricePerSession")
    @Mapping(target = "currency", source = "tea.currency")
    BookingDto toBookingDto(Booking entity);
}
