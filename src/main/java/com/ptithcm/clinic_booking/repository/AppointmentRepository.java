package com.ptithcm.clinic_booking.repository;

import com.ptithcm.clinic_booking.model.Appointment;
import com.ptithcm.clinic_booking.model.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findAllByCustomerId(Integer customerId);

    Page<Appointment> findByScheduleDate(LocalDate date, Pageable pageable);
    Page<Appointment> findByStatus(AppointmentStatus status, Pageable pageable);
    List<Appointment> findByScheduleId(Integer scheduleId);
    int countByScheduleId(Integer scheduleId);

    @Query("SELECT a FROM Appointment a WHERE a.note LIKE %:keyword% OR a.customer.name LIKE %:keyword%")
    Page<Appointment> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    Optional<Appointment> findByCustomerEmailAndCustomerPhoneAndScheduleId(String email, String phone, Integer scheduleId);

//    Page<Appointment> findByScheduleDoctorId(String doctorId, Pageable pageable);
//    Page<Appointment> findByScheduleIdAndScheduleDoctorId(String scheduleId, String doctorId, Pageable pageable);

}
