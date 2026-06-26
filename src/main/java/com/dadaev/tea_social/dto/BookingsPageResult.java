package com.dadaev.tea_social.dto;

import org.springframework.data.domain.Slice;

import java.util.List;

public record BookingsPageResult(
        List<BookingDto> items,
        Integer nextOffset,
        boolean hasMore
) {
    public static BookingsPageResult from(Slice<BookingDto> bookingSlice) {
        List<BookingDto> items = bookingSlice.getContent();
        Integer nextOffset = bookingSlice.hasNext()? (bookingSlice.getNumber() +1) * bookingSlice.getSize() : -1;
        boolean hasMore = bookingSlice.hasNext();

        return new BookingsPageResult(items, nextOffset, hasMore);
    }
}
