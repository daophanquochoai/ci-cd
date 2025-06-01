package com.ptithcm.clinic_booking.dto.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSimpleResponseDTO {

    @Schema(description = "ID bác sĩ", example = "D001")
    private String id;

    @Schema(description = "Mã chuyên khoa", example = "MS01")
    private String medicalSpecialtyId;

    @Schema(description = "Họ tên bác sĩ", example = "Nguyễn Văn A")
    private String name;

    @Schema(description = "Số điện thoại", example = "0912345678")
    private String phone;


    @Schema(description = "Email liên hệ", example = "doctor@example.com")
    private String email;

    @Schema(description = "Địa chỉ", example = "123 Đường ABC, Quận 1, TP.HCM")
    private String address;

    private Boolean gender;

    @Schema(description = "Trạng thái bác sĩ", example = "doctor.com")
    private String imageLink;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Ngày sinh", example = "1980-11-12")
    private LocalDate birthday;

    @Schema(description = "Mô tả chi tiết",
            example = "Chuyên gia về điều trị các bệnh lý về mắt, từ các bệnh thông thường đến các bệnh lý nghiêm trọng về mắt.")
    private String description;

    @Schema(description = "Trình độ chuyên môn", example = "Bác sĩ Chuyên khoa I")
    private String qualification;

    private LocalDateTime createdAt;

}
