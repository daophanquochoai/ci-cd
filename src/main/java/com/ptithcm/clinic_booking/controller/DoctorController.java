package com.ptithcm.clinic_booking.controller;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.doctor.DoctorSimpleResponseDTO;
import com.ptithcm.clinic_booking.model.ApiResponse;
import com.ptithcm.clinic_booking.service.DoctorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/p/doctors")
@RestController
@Validated
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorSimpleResponseDTO>> getDoctorById(@PathVariable  @NotBlank String id){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, doctorService.getDoctorSimpleById(id)));
    }

    @GetMapping("/by_medical_specialty")
    public ResponseEntity<ApiResponse<List<DoctorSimpleResponseDTO>>> getDoctorsByMedicalSpecialty(@RequestParam String medicalSpecialtyId){
        return ResponseEntity
                .ok(new ApiResponse<>(HttpStatus.OK, doctorService.getDoctorsByMedicalSpecialty(medicalSpecialtyId)));
    }

    @GetMapping("/active/all")
    public ResponseEntity<ApiResponse<List<DoctorSimpleResponseDTO>>> getAllActiveDoctors(){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, doctorService.getAllActiveDoctors()));
    }


    @GetMapping("/active/page")
    public ResponseEntity<ApiResponse<PageResponse<DoctorSimpleResponseDTO>>> getPageActiveDoctors(@ModelAttribute @Valid PaginationRequest pageRequest){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, doctorService.getPageActiveDoctors(pageRequest)));
    }
}
