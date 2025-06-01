package com.ptithcm.clinic_booking.controller;

import com.ptithcm.clinic_booking.dto.appointment.AppointmentCreateDTO;
import com.ptithcm.clinic_booking.dto.appointment.AppointmentDTO;
import com.ptithcm.clinic_booking.model.ApiResponse;
import com.ptithcm.clinic_booking.service.AppointmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/api/v1/p/appointments")
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/by-customer-schedule")
    public ResponseEntity<ApiResponse<AppointmentDTO>> getAppointmentByCustomerAndSchedule(@RequestParam String email,
                                                                                          @RequestParam String phone,
                                                                                          @RequestParam @NotBlank Integer scheduleId){
        AppointmentDTO appointment = appointmentService.getAppointmentByCustomerInfo(email, phone, scheduleId);
        return ResponseEntity.ok( new ApiResponse<>(HttpStatus.OK, appointment));
    }

    @GetMapping("/by-schedule")
    public ResponseEntity<ApiResponse<List<AppointmentDTO>>> getAppointmentsBySchedule(@NotBlank Integer scheduleId){
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsBySchedule(scheduleId);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, appointments));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addAppointment(@RequestBody @Valid AppointmentCreateDTO appointment){
        appointmentService.addAppointment(appointment);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED, "Đăng ký lịch hẹn khám bệnh thành công."));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateAppointment(@RequestBody @Valid AppointmentDTO appointment){
        appointmentService.updateAppointment(appointment);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "Chỉnh sửa thông tin lịch hẹn khám bệnh thành công."));
    }

    @PostMapping("/count-by-schedule")
    public ResponseEntity<ApiResponse<Integer>> countAppointmentsBySchedule(@NotBlank Integer scheduleId){
        int count = appointmentService.countAppointmentsBySchedule(scheduleId);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED, count));
    }
}
