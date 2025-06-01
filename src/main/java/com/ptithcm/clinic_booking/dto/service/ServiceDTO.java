package com.ptithcm.clinic_booking.dto.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ptithcm.clinic_booking.dto.specialty.MedicalSpecialtyResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {
    @Schema(description = "Mã dịch vụ", example = "SV001")
    private String id;

//    @NotBlank(message = "Mã người tạo không được để trống")
//    @Schema(description = "Mã người tạo dịch vụ", example = "manager1")
//    private ManagerResponseDTO managerResponseDTO;

    @NotBlank(message = "Chuyên khoa không được để trống")
    @Schema(description = "Chuyên khoa")
    private MedicalSpecialtyResponseDTO medicalSpecialty;

    @NotBlank(message = "Tên dịch vụ không được để trống")
    @Size(max = 100, message = "Tên dịch vụ tối đa 100 ký tự")
    @Schema(description = "Tên dịch vụ", example = "Khám tổng quát")
    private String name;

    @Schema(description = "Mô tả dịch vụ", example = "Dịch vụ khám tổng quát toàn diện")
    private String description;

    @NotBlank(message = "Trạng thái không được để trống")
    @Size(max = 15, message = "Trạng thái tối đa 15 ký tự")
    @Schema(description = "Trạng thái dịch vụ", example = "ACTIVE")
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Thời gian tạo", example = "2025-05-22T10:15:30")
    private LocalDateTime createdAt;

}
