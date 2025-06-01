package com.ptithcm.clinic_booking.controller;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.schedule.ScheduleDTO;
import com.ptithcm.clinic_booking.model.ApiResponse;
import com.ptithcm.clinic_booking.model.ScheduleStatus;
import com.ptithcm.clinic_booking.service.ScheduleService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/p/schedules")
@Validated
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ScheduleDTO>> getScheduleById(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, scheduleService.getScheduleById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ScheduleDTO>>> getAllSchedules(){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, scheduleService.getAllSchedules()));
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<PageResponse<ScheduleDTO>>> getSchedulesPage(@ModelAttribute PaginationRequest page){
        PageResponse<ScheduleDTO> schedules = scheduleService.getPageSchedules(page);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, schedules));
    }

    @GetMapping("/by_status/{status}")
    public ResponseEntity<ApiResponse<List<ScheduleDTO>>> getAllSchedulesByStatus(@PathVariable ScheduleStatus status){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, scheduleService.getSchedulesByStatus(status)));
    }

    @GetMapping("/by_doctor/{doctorId}")
    public ResponseEntity<ApiResponse<List<ScheduleDTO>>> getAllSchedulesByDoctor(@PathVariable @NotBlank String doctorId){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, scheduleService.getSchedulesByDoctor(doctorId)));
    }

}
