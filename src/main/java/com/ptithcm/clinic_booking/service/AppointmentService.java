package com.ptithcm.clinic_booking.service;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.appointment.AppointmentCreateDTO;
import com.ptithcm.clinic_booking.dto.appointment.AppointmentDTO;
import com.ptithcm.clinic_booking.model.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    //Chung
    AppointmentDTO getAppointmentById(String id);
    PageResponse<AppointmentDTO> getAppointmentsByDate(LocalDate date, PaginationRequest pageRequest);

    // Quản lý: phân trang
    PageResponse<AppointmentDTO> getPageAppointments(PaginationRequest pageRequest);
    PageResponse<AppointmentDTO> getAppointmentsByStatus(AppointmentStatus status, PaginationRequest pageRequest);
    PageResponse<AppointmentDTO> searchAppointments(String keyword, PaginationRequest pageRequest);
    void softDeleteAppointment(String appointmentId);

    // Khách hàng-public
    AppointmentDTO getAppointmentByCustomerInfo(String email, String phone, Integer scheduleId);
    void addAppointment(AppointmentCreateDTO appointmentDTO);
    void updateAppointment(AppointmentDTO appointmentDTO);

    // Bác sĩ- Quan lý
    List<AppointmentDTO> getAppointmentsBySchedule(Integer scheduleId);
    int countAppointmentsBySchedule(Integer scheduleId);
    void changeAppointmentStatus(String id, AppointmentStatus status);
    }


//    List<AppointmentDTO> getAppointmentsByDoctor(String doctorId);
//    List<AppointmentDTO> getAppointmentsByDoctorAndSchedule(String doctorId, String scheduleId);