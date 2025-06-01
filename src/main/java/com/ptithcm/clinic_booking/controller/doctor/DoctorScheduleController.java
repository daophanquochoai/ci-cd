package com.ptithcm.clinic_booking.controller.doctor;

import com.ptithcm.clinic_booking.dto.schedule.ScheduleCreateDTO;
import com.ptithcm.clinic_booking.dto.schedule.ScheduleDTO;
import com.ptithcm.clinic_booking.model.ApiResponse;
import com.ptithcm.clinic_booking.model.ScheduleStatus;
import com.ptithcm.clinic_booking.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/d/doctor-schedules")
@Validated
public class DoctorScheduleController {
    private final ScheduleService scheduleService;

    public DoctorScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addSchedule(@RequestBody @Valid ScheduleCreateDTO schedule){
        scheduleService.addSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED,"Thêm lịch trình thành công."));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateSchedule(@RequestBody @Valid ScheduleDTO schedule){
        scheduleService.updateSchedule(schedule);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,"Cập nhật lịch trình thành công."));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<ApiResponse<String>> cancelSchedule(@PathVariable @NotBlank Integer id) {
        scheduleService.changeStatusSchedule(id, ScheduleStatus.CANCELED);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,"Hủy lịch trình thành công."));
    }

    @PutMapping("/pause/{id}")
    public ResponseEntity<ApiResponse<String>> pauseSchedule(@PathVariable @NotBlank Integer id) {
        scheduleService.changeStatusSchedule(id, ScheduleStatus.CANCELED);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,"Tạm hoãn lịch trình thành công."));
    }

    @PutMapping("/resume/{id}")
    public ResponseEntity<ApiResponse<String>> resumeSchedule(@PathVariable @NotBlank Integer id) {
        scheduleService.changeStatusSchedule(id, ScheduleStatus.ACTIVE);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,"Mở lại lịch trình thành công."));
    }
}
