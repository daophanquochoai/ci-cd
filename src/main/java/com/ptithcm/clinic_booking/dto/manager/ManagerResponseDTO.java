package com.ptithcm.clinic_booking.dto.manager;

import com.ptithcm.clinic_booking.dto.account.AccountResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerResponseDTO {
    @Schema(description = "Mã quản lý", example = "M001", maxLength = 10)
    private String id;

    @Schema(description = "Thông tin tài khoản")
    private AccountResponseDTO account;

    @Schema(description = "Tên khách hàng", example = "Nguyễn Văn A", maxLength = 100)
    private String name;

    @Schema(description = "Số điện thoại", example = "0912345678", pattern = "^(0|\\+84)[0-9]{9,10}$")
    private String phone;

    @Schema(description = "Email khách hàng", example = "nguyenvana@example.com", maxLength = 100)
    private String email;

    @Schema(description = "Địa chỉ khách hàng", example = "123 Đường ABC, Quận 1, TP.HCM", maxLength = 255)
    private String address;

    @Schema(description = "Giới tính (true = nam, false = nữ)", example = "true")
    private Boolean gender;

    @Schema(description = "Trạng thái khách hàng", example = "ACTIVE", allowableValues = {"ACTIVE", "BLOCKED", "DELETED"})
    private String status;

    @Schema(description = "Thời gian tạo", example = "2025-05-29T14:30:00")
    private LocalDateTime createdAt;

}
