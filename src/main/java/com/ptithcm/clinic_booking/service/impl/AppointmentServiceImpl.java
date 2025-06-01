package com.ptithcm.clinic_booking.service.impl;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.appointment.AppointmentCreateDTO;
import com.ptithcm.clinic_booking.dto.appointment.AppointmentDTO;
import com.ptithcm.clinic_booking.dto.customer.CustomerDTO;
import com.ptithcm.clinic_booking.exception.ResourceNotFoundException;
import com.ptithcm.clinic_booking.mapper.AppointmentMapper;
import com.ptithcm.clinic_booking.mapper.CustomerMapper;
import com.ptithcm.clinic_booking.model.Appointment;
import com.ptithcm.clinic_booking.model.AppointmentStatus;
import com.ptithcm.clinic_booking.model.Schedule;
import com.ptithcm.clinic_booking.repository.AppointmentRepository;
import com.ptithcm.clinic_booking.repository.ScheduleRepository;
import com.ptithcm.clinic_booking.service.AppointmentService;
import com.ptithcm.clinic_booking.service.CustomerService;
import com.ptithcm.clinic_booking.service.OfferingService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final CustomerService customerService;
    private final OfferingService offeringService;
    private final ScheduleRepository scheduleRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, CustomerService customerService,
                                  OfferingService offeringService, ScheduleRepository scheduleRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerService = customerService;
        this.offeringService = offeringService;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public AppointmentDTO getAppointmentById(String id) {
        Appointment appointment = appointmentRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        return AppointmentMapper.toAppointmentDTO(appointment);
    }

    @Override
    public PageResponse<AppointmentDTO> getAppointmentsByDate(LocalDate date, PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();

        Page<Appointment> page = appointmentRepository.findByScheduleDate(date, pageable);
        return new PageResponse<>(page.map(AppointmentMapper::toAppointmentDTO));
    }

    @Override
    public PageResponse<AppointmentDTO> getPageAppointments(PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();

        Page<Appointment> page = appointmentRepository.findAll(pageable);
        List<AppointmentDTO> appointment =page.stream().
                map(AppointmentMapper::toAppointmentDTO)
                .toList();
        return new PageResponse<>(appointment, page);
    }

    @Override
    public PageResponse<AppointmentDTO> getAppointmentsByStatus(AppointmentStatus status, PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();

        Page<Appointment> page = appointmentRepository.findByStatus(status, pageable);
        return new PageResponse<>(page.map(AppointmentMapper::toAppointmentDTO));
    }

    @Override
    public PageResponse<AppointmentDTO> searchAppointments(String keyword, PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<Appointment> page = appointmentRepository.searchByKeyword(keyword, pageable);
        return new PageResponse<>(page.map(AppointmentMapper::toAppointmentDTO));
    }

    @Override
    public AppointmentDTO getAppointmentByCustomerInfo(String email, String phone, Integer scheduleId) {
        Appointment appointment = appointmentRepository
                .findByCustomerEmailAndCustomerPhoneAndScheduleId(email, phone, scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with given customer info"));
        return AppointmentMapper.toAppointmentDTO(appointment);
    }

    @Transactional
    @Override
    public void addAppointment(AppointmentCreateDTO appointmentDTO) {
        offeringService.getServiceById(appointmentDTO.getServiceId());

        Schedule schedule = scheduleRepository.findById(appointmentDTO.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        Appointment appointment = AppointmentMapper.toAppointment(appointmentDTO);
        appointment.setSchedule(schedule);
        CustomerDTO savedCustomer = customerService.addCustomer(appointmentDTO.getCustomer());

        appointment.setCustomer(CustomerMapper.toCustomer(savedCustomer));
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointmentRepository.save(appointment);
        System.err.println("save thành công");
    }

    @Override
    public List<AppointmentDTO> getAppointmentsBySchedule(Integer scheduleId) {
        List<Appointment> appointments = appointmentRepository.findByScheduleId(scheduleId);
        return appointments.stream()
                .map(AppointmentMapper::toAppointmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public int countAppointmentsBySchedule(Integer scheduleId) {
        return appointmentRepository.countByScheduleId(scheduleId);
    }

    @Override
    public void changeAppointmentStatus(String id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch hẹn với ID: " + id));
        appointment.setStatus(status);
        appointmentRepository.save(appointment);
    }

    @Override
    public void updateAppointment(AppointmentDTO appointmentDTO) {
        appointmentRepository.findById(appointmentDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch hẹn với ID: " + appointmentDTO.getId()));
        Appointment appointment = AppointmentMapper.toAppointment(appointmentDTO);
        appointmentRepository.save(appointment);
    }

    @Override
    public void softDeleteAppointment(String id) {
        Appointment appointment = appointmentRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch hẹn với ID: " + id));
        appointment.setStatus(AppointmentStatus.DELETED);
        appointmentRepository.save(appointment);
    }
}
