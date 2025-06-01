package com.ptithcm.clinic_booking.controller;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.clinic.ClinicDTO;
import com.ptithcm.clinic_booking.model.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ptithcm.clinic_booking.service.ClinicService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/p/clinics")
@Validated
public class ClinicController {
    private final ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClinicDTO>> getClinicById(@PathVariable  @NotBlank String id) {
        ClinicDTO clinic = clinicService.getClinicById(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, clinic));
    }

    @GetMapping("/active/all")
    public ResponseEntity<ApiResponse<List<ClinicDTO>>> getAllClinicActive() {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, clinicService.getAllActiveClinics()));
    }

    @GetMapping("/active/page")
    public ResponseEntity<ApiResponse<PageResponse<ClinicDTO>>> getAllClinicActive(PaginationRequest pageRequest) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, clinicService.getPageActiveClinics(pageRequest)));
    }

}
