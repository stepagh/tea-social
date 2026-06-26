package com.dadaev.tea_social.Repository;

import com.dadaev.tea_social.model.Booking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
        SELECT b.timeSlot FROM Booking b
        WHERE b.teaHouse.id = :teaHouseId
        AND b.date = :date""")
    List<LocalTime> findBookedSlotsByDateAndTeaHouseId(@Param("date") LocalDate date, @Param("teaHouseId") Long teaHouseId);

    @Query("""
    SELECT b FROM Booking b
    JOIN FETCH b.teaHouse 
    JOIN FETCH b.tea
    JOIN FETCH b.userProfile
    WHERE b.userProfile.id = :id
    """)
    Slice<Booking> findBookingsByUserProfile_Id(Long id, Pageable pageable);
    boolean existsByTimeSlotAndDate(LocalTime timeSlot, LocalDate date);
}

