package com.ptithcm.clinic_booking.mapper;

import com.ptithcm.clinic_booking.dto.appointment.AppointmentCreateDTO;
import com.ptithcm.clinic_booking.dto.appointment.AppointmentDTO;
import com.ptithcm.clinic_booking.model.Appointment;
import com.ptithcm.clinic_booking.model.Schedule;
import com.ptithcm.clinic_booking.model.Service;

public class AppointmentMapper {
    public static Appointment toAppointment(AppointmentDTO dto){
        if (dto == null) return null;
        Appointment appointment = new Appointment();

        appointment.setId(dto.getId());
        appointment.setService(ServiceMapper.toService(dto.getService()));
        appointment.setSchedule(ScheduleMapper.toSchedule(dto.getSchedule()));
        appointment.setCustomer(CustomerMapper.toCustomer(dto.getCustomer()));
        appointment.setNumericalOrder(dto.getNumericalOrder());
        appointment.setNote(dto.getNote());
        appointment.setStatus(dto.getStatus());
        appointment.setUpdatedAt(dto.getUpdatedAt());
        appointment.setUpdatedBy(dto.getUpdatedBy());
        return appointment;
    }

    public static AppointmentDTO toAppointmentDTO(Appointment appointment){
        if (appointment == null) return null;
        AppointmentDTO dto = new AppointmentDTO();

        dto.setId(appointment.getId());
        dto.setService(ServiceMapper.toServiceDTO(appointment.getService()));
        dto.setSchedule(ScheduleMapper.toScheduleDTO(appointment.getSchedule()));
        dto.setCustomer(CustomerMapper.toCustomerDTO(appointment.getCustomer()));
        dto.setNumericalOrder(appointment.getNumericalOrder());
        dto.setNote(appointment.getNote());
        dto.setStatus(appointment.getStatus());
        dto.setUpdatedAt(appointment.getUpdatedAt());
        dto.setUpdatedBy(appointment.getUpdatedBy());
        dto.setCreatedAt(appointment.getCreatedAt());

        return dto;
    }

    public static Appointment toAppointment(AppointmentCreateDTO dto){
        if (dto == null) return null;
        Appointment appointment = new Appointment();

        Service service = new Service(dto.getServiceId());
        appointment.setService(service);

        Schedule schedule = new Schedule(dto.getScheduleId());
        appointment.setSchedule(schedule);

        appointment.setCustomer(CustomerMapper.toCustomer(dto.getCustomer()));
        appointment.setNumericalOrder(dto.getNumericalOrder());
        appointment.setNote(dto.getNote());
        appointment.setStatus(dto.getStatus());
        appointment.setUpdatedBy(dto.getUpdatedByUser());
        return appointment;
    }
}
