package com.ptithcm.clinic_booking.controller.manager;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.clinic.ClinicDTO;
import com.ptithcm.clinic_booking.dto.clinic.ClinicCreateDTO;
import com.ptithcm.clinic_booking.model.ApiResponse;
import com.ptithcm.clinic_booking.service.ClinicService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/m/clinics")
@Validated
public class ClinicManageController {
    private final ClinicService clinicService;

    @Autowired
    public ClinicManageController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ClinicDTO>>> getAllClinics() {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, clinicService.getAllClinics()));
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<PageResponse<ClinicDTO>>> getPageClinics(@ModelAttribute @Valid PaginationRequest pageRequest) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, clinicService.getPageClinics(pageRequest)));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addClinic(@RequestBody @Valid ClinicCreateDTO clinic){
        clinicService.addClinic(clinic);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED,"Thêm phòng khám thành công."));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateClinic(@RequestBody @Valid ClinicDTO clinic){
        clinicService.updateClinic(clinic);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "Cập nhật phòng khám thành công."));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> softDeleteClinic(@PathVariable  @NotBlank String id){
        clinicService.softDeleteClinic(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "Phòng khám đã được xóa."));
    }
}
