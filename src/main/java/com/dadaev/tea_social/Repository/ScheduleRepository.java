package com.dadaev.tea_social.Repository;

import com.dadaev.tea_social.model.TeaHouseSchedule;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<TeaHouseSchedule, Long> {
        @Lock(LockModeType.PESSIMISTIC_WRITE)
        Optional<TeaHouseSchedule> findByDayOfWeekAndTeaHouse_Id(DayOfWeek dayOfWeek, Long teaHouseId);
}
