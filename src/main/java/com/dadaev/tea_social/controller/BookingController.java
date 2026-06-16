package com.dadaev.tea_social.controller;

import com.dadaev.tea_social.Service.BookingService;
import com.dadaev.tea_social.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/tea-houses")
    public ResponseEntity<List<TeaHouseResponse>> getTeaHouses() {
        return ResponseEntity.ok(bookingService.getTeaHouses());
    }

    @GetMapping("/tea-houses/{teaHouseId}/catalog")
    public ResponseEntity<List<BookableTeaResponse>> getCatalog(@PathVariable() Long teaHouseId) {
        return ResponseEntity.ok(bookingService.getCatalog(teaHouseId));
    }

    @GetMapping("tea-houses/{teaHouseId}/slots")
    public ResponseEntity<List<TimeSlotResponse>> getTimeSlots(@PathVariable Long teaHouseId, @RequestParam(name = "date") LocalDate date) {
         return ResponseEntity.ok(bookingService.getTimeSlots(teaHouseId, date));
    }

    @PostMapping()
    public ResponseEntity<BookingResponse> postBooking(@RequestBody BookingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.postBooking(request));
    }
}
