package com.ptithcm.clinic_booking.service.impl;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.schedule.ScheduleCreateDTO;
import com.ptithcm.clinic_booking.dto.schedule.ScheduleDTO;
import com.ptithcm.clinic_booking.mapper.ScheduleMapper;
import com.ptithcm.clinic_booking.exception.ResourceNotFoundException;
import com.ptithcm.clinic_booking.model.Schedule;
import com.ptithcm.clinic_booking.model.ScheduleStatus;
import com.ptithcm.clinic_booking.repository.ScheduleRepository;
import com.ptithcm.clinic_booking.service.ScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleDTO getScheduleById(Integer id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch trình với ID: " + id));
        return ScheduleMapper.toScheduleDTO(schedule);
    }

    @Override
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream()
                .map(ScheduleMapper::toScheduleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public   PageResponse<ScheduleDTO> getPageSchedules(PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<Schedule> page = scheduleRepository.findAll(pageable);
        List<ScheduleDTO> scheduleDTO = page.getContent().stream()
                            .map(ScheduleMapper::toScheduleDTO)
                            .collect(Collectors.toList());
        return new PageResponse<>(scheduleDTO, page);
    }

    @Override
    public List<ScheduleDTO> getSchedulesByStatus(ScheduleStatus status) {
        List<Schedule> schedules = scheduleRepository.findByStatus(status);
        if(schedules == null) throw new ResourceNotFoundException("Không lấy được danh sách lịch trình theo trạng thái:"+ status);
        return schedules.stream()
                .map(ScheduleMapper::toScheduleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> getSchedulesByDoctor(String doctorId) {
        List<Schedule> schedules = scheduleRepository.findByDoctorId(doctorId);
        if(schedules == null) throw new ResourceNotFoundException("Không lấy được danh sách lịch trình theo bác sĩ có id:"+ doctorId);
        return schedules.stream()
                .map(ScheduleMapper::toScheduleDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void addSchedule(ScheduleCreateDTO scheduleDTO) {

        if(scheduleDTO == null) throw new IllegalArgumentException("Dữ liệu lịch trình không hợp lệ.");
        Schedule schedule = ScheduleMapper.toSchedule(scheduleDTO);
//        schedule.setId(createScheduleId());
        scheduleRepository.save(schedule);
    }

//    private String createScheduleId() {
//        long countSchedule = scheduleRepository.count();
//        return String.format("SCHE%010d",countSchedule + 1);
//    }

    @Override
    public void updateSchedule(ScheduleDTO scheduleDTO) {

        scheduleRepository.findById(scheduleDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch trình với ID: " + scheduleDTO.getId()));

        Schedule schedule = ScheduleMapper.toSchedule(scheduleDTO);
        scheduleRepository.save(schedule);
    }

    @Override
    public void changeStatusSchedule(Integer id, ScheduleStatus status) {

        Schedule s = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không thể xóa. Không tìm thấy lịch trình với ID: " + id));

        status = checkScheduleStatus(s, status);
        s.setStatus(status);
        scheduleRepository.save(s);
    }

    private ScheduleStatus checkScheduleStatus(Schedule schedule, ScheduleStatus newStatus){
        ScheduleStatus currentStatus = schedule.getStatus();
        if(currentStatus == ScheduleStatus.EXPIRED)
            throw new IllegalStateException("Lịch đã hết hạn.");

        LocalDateTime startDateTime = LocalDateTime.of(schedule.getDate(), schedule.getTimeStart());
        LocalDateTime endDateTime = LocalDateTime.of(schedule.getDate(), schedule.getTimeEnd());
        LocalDateTime now = LocalDateTime.now();


        switch(newStatus) {
            case CANCELED:
                if (now.isAfter(endDateTime)){
                    throw new IllegalStateException("Không thể hủy lịch đã quá hạn.");
                }
                return ScheduleStatus.CANCELED;
            case PAUSED:
                if (now.isAfter(endDateTime)) {
                    throw new IllegalStateException("Không thể tạm dừng lịch đã quá hạn.");
                }
                if (currentStatus == ScheduleStatus.PAUSED) {
                    throw new IllegalStateException("Lịch đã tạm dừng rồi.");
                }
                return ScheduleStatus.PAUSED;
            case ACTIVE: // Resume từ PAUSED
                if (currentStatus == ScheduleStatus.CANCELED) {
                    throw new IllegalStateException("Không thể thay đổi trạng thái vì lịch trình này đã bị hủy.");
                }
                if(currentStatus == ScheduleStatus.DELETED){
                    throw new IllegalStateException("Không thể thay đổi trạng thái vì lịch trình này đã bị xóa.");
                }
                // Tự động tính lại trạng thái theo thời gian
                if (now.isBefore(startDateTime)) {
                    return ScheduleStatus.UPCOMING;
                } else if (now.isAfter(endDateTime)) {
                    return ScheduleStatus.EXPIRED;
                } else {
                    return ScheduleStatus.ONGOING;
                }
            default:
                return newStatus;
        }
    }

    @Override
    public void softDeleteSchedule(Integer id) {
        Schedule s = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không thể xóa. Không tìm thấy lịch trình với ID: " + id));
        s.setStatus(ScheduleStatus.DELETED);
        scheduleRepository.save(s);
    }

    @Override
    public boolean isDoctorAvailable(String doctorId, LocalDate date) {
        long count = scheduleRepository.countByDoctorAndDate(doctorId, date);
        return count == 0; // Bác sĩ có thể tham gia nếu không có lịch nào tại thời gian đ
    }

//    @Override
//    public boolean isServiceAvailable(String serviceId, LocalDateTime time) {
//        long count = scheduleRepository.countByServiceAndTime(serviceId, time);
//        return count == 0; // Bác sĩ có thể tham gia nếu không có lịch nào tại thời gian đ
//    }
}
