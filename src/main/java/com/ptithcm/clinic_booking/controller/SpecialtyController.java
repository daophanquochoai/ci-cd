package com.ptithcm.clinic_booking.controller;

import com.google.api.Page;
import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.specialty.BaseMedicalSpecialtyDTO;
import com.ptithcm.clinic_booking.dto.specialty.MedicalSpecialtyRequestDTO;
import com.ptithcm.clinic_booking.dto.specialty.MedicalSpecialtyResponseDTO;
import com.ptithcm.clinic_booking.model.ApiResponse;
import com.ptithcm.clinic_booking.service.MedicalSpecialtyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/p/medical-specialties")
@Validated
public class SpecialtyController {
    private final MedicalSpecialtyService mSpecialtyService;

    @Autowired
    public SpecialtyController(MedicalSpecialtyService mSpecialtyService) {
        this.mSpecialtyService = mSpecialtyService;
    }

    @GetMapping("/active/all")
    public ResponseEntity<ApiResponse<List<BaseMedicalSpecialtyDTO>>> getAllActiveMSpecialties(){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,
                mSpecialtyService.getAllActiveMSpecialties()));
    }

    @GetMapping("/active/page")
    public ResponseEntity<ApiResponse<PageResponse<BaseMedicalSpecialtyDTO>>> getPageActiveMSpecialties(@ModelAttribute @Valid PaginationRequest pageRequest){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,
                mSpecialtyService.getPageActiveMSpecialties(pageRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BaseMedicalSpecialtyDTO>> getMSpecialtyById(@PathVariable  @NotBlank String id){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,
                mSpecialtyService.getMSpecialtyById(id)));
    }
}
