package com.dadaev.tea_social.Service;

import com.dadaev.tea_social.Repository.BookableTeaRepository;
import com.dadaev.tea_social.Repository.BookingRepository;
import com.dadaev.tea_social.Repository.ScheduleRepository;
import com.dadaev.tea_social.Repository.TeaHouseRepository;
import com.dadaev.tea_social.dto.*;
import com.dadaev.tea_social.exceptions.BookingAlreadyExistsException;
import com.dadaev.tea_social.exceptions.ResourceNotFoundException;
import com.dadaev.tea_social.mapper.BookableTeaMapper;
import com.dadaev.tea_social.mapper.BookingMapper;
import com.dadaev.tea_social.mapper.TeaHouseMapper;
import com.dadaev.tea_social.model.BookableTea;
import com.dadaev.tea_social.model.Booking;
import com.dadaev.tea_social.model.TeaHouse;
import com.dadaev.tea_social.model.TeaHouseSchedule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final TeaHouseRepository teaHouseRepository;
    private final TeaHouseMapper teaHouseMapper;
    private final BookableTeaRepository bookableTeaRepository;
    private final BookableTeaMapper bookableTeaMapper;
    private final ScheduleRepository scheduleRepository;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public List<TeaHouseResponse> getTeaHouses() {
        return teaHouseRepository.findAll().stream().map(teaHouseMapper::toResponse).toList();
    }

    public List<BookableTeaResponse> getCatalog(Long teaHouseId) {
        return bookableTeaRepository.findAllByTeaHouse_id(teaHouseId).stream().map(bookableTeaMapper::toResponse).toList();
    }
    @Transactional
    public List<TimeSlotResponse> getTimeSlots(Long teaHouseId, LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        TeaHouseSchedule schedule = scheduleRepository.findByDayOfWeekAndTeaHouse_Id(dayOfWeek, teaHouseId)
                .orElseThrow(() -> new ResourceNotFoundException("The tea house doesn't work or the schedule is not configured"));

        List<LocalTime> bookedSlots = bookingRepository.findBookedSlotsByDateAndTeaHouseId(date, teaHouseId);
        List<TimeSlotResponse> slots = new ArrayList<>();
        LocalTime currentSlot = schedule.getStartTime();
        LocalTime endTime = schedule.getEndTime();
        int duration = schedule.getSlotDurationMinutes();

        while (currentSlot.plusMinutes(duration).isBefore(endTime) || currentSlot.plusMinutes(duration).equals(endTime)) {
            if (bookedSlots.contains(currentSlot)) {
                currentSlot = currentSlot.plusMinutes(duration);
                continue;
            }
            if (date.equals(LocalDate.now()) && currentSlot.isBefore(LocalTime.now())) {
                currentSlot = currentSlot.plusMinutes(duration);
                continue;
            }

            slots.add(new TimeSlotResponse(currentSlot));
            currentSlot = currentSlot.plusMinutes(duration);
        }
        return slots;
    }

    @Transactional
    public BookingResponse postBooking(BookingRequest request) {
        TeaHouseSchedule schedule = scheduleRepository
                .findByDayOfWeekAndTeaHouse_Id(request.date().getDayOfWeek(), request.teaHouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Tea house schedule not found"));

        TeaHouse teaHouse = teaHouseRepository.findById(request.teaHouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Tea house not found"));

        BookableTea bookableTea = bookableTeaRepository.findById(request.teaId())
                .orElseThrow(() -> new ResourceNotFoundException("Bookable tea not found"));

        if (bookingRepository.existsByTimeSlotAndDate(request.timeSlot(), request.date()))
            throw new BookingAlreadyExistsException("Booking already exists");
        Booking booking = bookingMapper.toEntity(request, teaHouse, bookableTea);
        bookingRepository.save(booking);
        return bookingMapper.toResponse(booking);
    }

}
