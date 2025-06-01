package com.ptithcm.clinic_booking.controller.manager;


import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.specialty.MedicalSpecialtyRequestDTO;
import com.ptithcm.clinic_booking.dto.specialty.MedicalSpecialtyResponseDTO;
import com.ptithcm.clinic_booking.model.ApiResponse;
import com.ptithcm.clinic_booking.service.MedicalSpecialtyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/m/medical-specialties")
@Validated
public class SpecialtyManageController {
    private final MedicalSpecialtyService mSpecialtyService;

    public SpecialtyManageController(MedicalSpecialtyService mSpecialtyService) {
        this.mSpecialtyService = mSpecialtyService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<MedicalSpecialtyResponseDTO>>> getAllMSpecialties(){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,
                mSpecialtyService.getAllMSpecialties()));
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<PageResponse<MedicalSpecialtyResponseDTO>>> getPageMSpecialties(@ModelAttribute @Valid PaginationRequest pageRequest){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,
                mSpecialtyService.getPageMSpecialties(pageRequest)));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateMSpecialty(@RequestBody @Valid MedicalSpecialtyRequestDTO specialtyRequestDTO){
        mSpecialtyService.updateMSpecialty(specialtyRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,"Cập nhật chuyên khoa thành công."));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addMSpecialty(@RequestBody @Valid MedicalSpecialtyRequestDTO specialtyRequestDTO){
        mSpecialtyService.addMSpecialty(specialtyRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED,"Thêm chuyên khoa thành công."));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> softDeleteSpecialty(@PathVariable @NotBlank String id){
        mSpecialtyService.softDeleteMSpecialty(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "Chuyên khoa đã được xóa."));
    }

}
