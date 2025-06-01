package com.ptithcm.clinic_booking.dto.account;

import com.ptithcm.clinic_booking.dto.RoleDTO;
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
public class AccountResponseDTO {
    @Schema(description = "Tên đăng nhập của tài khoản", example = "manager1")
    private String username;

    @Schema( description = "Vai trò của tài khoản (chỉ cho phép MANAGER và DOCTOR)", example = "2")
    private RoleDTO role;

    @Schema( description = "Trạng thái của tài khoản (ACTIVE, BLOCKED hoặc DELETED)",  example = "ACTIVE")
    private String status;
    private LocalDateTime createdAt;

}
