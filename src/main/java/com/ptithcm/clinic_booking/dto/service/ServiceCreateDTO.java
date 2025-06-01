package com.ptithcm.clinic_booking.dto.service;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCreateDTO {

    @NotBlank(message = "Mã người tạo không được để trống")
    @Schema(description = "Mã người tạo dịch vụ", example = "manager1")
    private String creatorId;

    @NotBlank(message = "Mã chuyên khoa không được để trống")
    @Schema(description = "Mã chuyên khoa", example = "MS01")
    private String medicalSpecialtyId;

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
}
