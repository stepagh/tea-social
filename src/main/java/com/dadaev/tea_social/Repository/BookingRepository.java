package com.dadaev.tea_social.Repository;

import com.dadaev.tea_social.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
        SELECT b.timeSlot FROM Booking b
        WHERE b.teaHouse.id = :teaHouseId
        AND b.date = :date""")
    List<LocalTime> findBookedSlotsByDateAndTeaHouseId(@Param("date") LocalDate date, @Param("teaHouseId") Long teaHouseId);


    boolean existsByTimeSlotAndDate(LocalTime timeSlot, LocalDate date);
}

