package com.ptithcm.clinic_booking.dto.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ptithcm.clinic_booking.dto.account.AccountRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorCreateDTO {

    @NotNull(message = "Không được để trống mã tài khoản")
    @Schema(description = "Tài khoản đăng nhập hệ thống")
    private AccountRequestDTO account;

    @NotBlank(message = "Không được để trống mã chuyên khoa")
    @Schema(description = "Mã chuyên khoa", example = "MS01")
    private String medicalSpecialtyId;

    @NotBlank(message = "Không được để trống họ tên")
    @Size(max = 100, message = "Họ tên tối đa 100 ký tự")
    @Schema(description = "Họ tên bác sĩ", example = "Nguyễn Văn A")
    private String name;

    @NotBlank(message = "Không được để trống số điện thoại")
    @Size(max = 15, message = "Số điện thoại tối đa 15 ký tự")
    @Schema(description = "Số điện thoại", example = "0912345678")
    private String phone;

    @NotBlank(message = "Không được để trống email")
    @Email(message = "Email không hợp lệ")
    @Size(max = 100, message = "Email tối đa 100 ký tự")
    @Schema(description = "Email liên hệ", example = "doctor@example.com")
    private String email;

    @Size(max = 255, message = "Địa chỉ tối đa 255 ký tự")
    @Schema(description = "Địa chỉ", example = "123 Đường ABC, Quận 1, TP.HCM")
    private String address;

    @Schema(description = "Giới tính", example = "true")
    private Boolean gender;

    @NotBlank(message = "Không được để trống trạng thái")
    @Size(max = 15, message = "Trạng thái tối đa 15 ký tự")
    @Schema(description = "Trạng thái bác sĩ", example = "ACTIVE")
    @Pattern(regexp = "^(ACTIVE|BLOCKED|DELETED)$", message = "Trạng thái phải là ACTIVE, BLOCKED hoặc DELETED")
    private String status;

//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    @Schema(description = "Thời gian tạo", example = "2025-05-22T10:15:30")
//    private LocalDateTime createdAt;

//    @Schema(description = "Ảnh đại diện (file upload)", type = "string", format = "binary")
//    private MultipartFile imageFile;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Ngày sinh", example = "1980-11-12")
    private LocalDate birthday;

    @Schema(description = "Mô tả chi tiết",
            example = "Chuyên gia về điều trị các bệnh lý về mắt, từ các bệnh thông thường đến các bệnh lý nghiêm trọng về mắt.")
    private String description;

    @Schema(description = "Trình độ chuyên môn", example = "Bác sĩ Chuyên khoa I")
    private String qualification;
}
