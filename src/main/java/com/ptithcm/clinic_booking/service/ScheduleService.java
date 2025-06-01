package com.ptithcm.clinic_booking.service;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.schedule.ScheduleCreateDTO;
import com.ptithcm.clinic_booking.dto.schedule.ScheduleDTO;
import com.ptithcm.clinic_booking.model.ScheduleStatus;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    ScheduleDTO getScheduleById(Integer id);
    List<ScheduleDTO> getAllSchedules();
    PageResponse<ScheduleDTO> getPageSchedules(PaginationRequest page);
    List<ScheduleDTO> getSchedulesByStatus(ScheduleStatus status);
    List<ScheduleDTO> getSchedulesByDoctor(String doctorId);
//    List<ScheduleDTO> getSchedulesByService(String serviceId);

    void addSchedule(ScheduleCreateDTO scheduleDTO);
    void updateSchedule(ScheduleDTO scheduleDTO);
    void changeStatusSchedule(Integer id, ScheduleStatus status);
    void softDeleteSchedule(Integer id);

    boolean isDoctorAvailable(String doctorId, LocalDate date);
//    boolean isServiceAvailable(String serviceId, LocalDateTime time);
}
