package com.ptithcm.clinic_booking.mapper;

import com.ptithcm.clinic_booking.dto.schedule.ScheduleCreateDTO;
import com.ptithcm.clinic_booking.dto.schedule.ScheduleDTO;
import com.ptithcm.clinic_booking.model.Clinic;
import com.ptithcm.clinic_booking.model.Doctor;
import com.ptithcm.clinic_booking.model.Schedule;
import com.ptithcm.clinic_booking.model.ScheduleStatus;

public class ScheduleMapper {
    public static ScheduleDTO toScheduleDTO(Schedule schedule) {
        if (schedule == null) return null;
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setDoctor(DoctorMapper.toDoctorSimpleDTO(schedule.getDoctor()));
        dto.setClinic(ClinicMapper.toClinicDTO(schedule.getClinic()));
        dto.setDate(schedule.getDate());
        dto.setTimeStart(schedule.getTimeStart());
        dto.setTimeEnd(schedule.getTimeEnd());
        dto.setMaxBooking(schedule.getMaxBooking());
        dto.setStatus(schedule.getStatus());
        dto.setCreatedAt(schedule.getCreatedAt());
        return dto;
    }

    public static Schedule toSchedule(ScheduleDTO dto) {
        if (dto == null) return null;
        Schedule schedule = new Schedule();
        schedule.setId(dto.getId());
        schedule.setDate(dto.getDate());
        schedule.setTimeStart(dto.getTimeStart());
        schedule.setTimeEnd(dto.getTimeEnd());
        schedule.setMaxBooking(dto.getMaxBooking());
        schedule.setStatus(dto.getStatus() != null ? dto.getStatus() : ScheduleStatus.UPCOMING);
        if (dto.getDoctor() != null && dto.getDoctor().getId() != null) {
            Doctor doctor = new Doctor();
            doctor.setId(dto.getDoctor().getId());
            schedule.setDoctor(doctor);
        }
        if (dto.getClinic() != null && dto.getClinic().getId() != null) {
            Clinic clinic = new Clinic();
            clinic.setId(dto.getClinic().getId());
            schedule.setClinic(clinic);
        }
        return schedule;
    }

    public static Schedule toSchedule(ScheduleCreateDTO dto) {
        if (dto == null) return null;
        Schedule schedule = new Schedule();
        schedule.setDate(dto.getDate());
        schedule.setTimeStart(dto.getTimeStart());
        schedule.setTimeEnd(dto.getTimeEnd());
        schedule.setMaxBooking(dto.getMaxBooking());
        schedule.setStatus(dto.getStatus() != null ? dto.getStatus() : ScheduleStatus.UPCOMING);

        Doctor doctor = new Doctor(dto.getDoctorId());
        schedule.setDoctor(doctor);

        Clinic clinic = new Clinic(dto.getClinicId());
        schedule.setClinic(clinic);
        return schedule;
    }
}
