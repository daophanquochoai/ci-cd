package com.ptithcm.clinic_booking.repository;

import com.ptithcm.clinic_booking.model.Schedule;
import com.ptithcm.clinic_booking.model.ScheduleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByStatus(ScheduleStatus status);

    List<Schedule> findByDoctorId(String doctorId);

//    List<Schedule> findByServiceId(String serviceId);

    @Query("SELECT COUNT(s) FROM Schedule s WHERE s.doctor.id = :doctorId AND s.date = :date")
    long countByDoctorAndDate( @Param("doctorId") String doctorId, @Param("date") LocalDate date);
}
