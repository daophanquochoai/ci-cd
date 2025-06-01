package com.ptithcm.clinic_booking.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ResetPasswordRequestDTO", description = "DTO dùng để reset mật khẩu với email, otp và mật khẩu mới")
public class ResetPasswordRequestDTO {

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    @Schema(description = "Email người dùng", example = "user@example.com")
    private String email;

    @NotBlank(message = "OTP không được để trống")
    @Schema(description = "Mã OTP xác thực", example = "123456", required = true)
    private String otp;

    @NotBlank(message = "Mật khẩu mới không được để trống")
    @Size(min = 8, max = 100, message = "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ thường, chữ hoa và số.")
    @Schema(description = "Mật khẩu mới của người dùng", example = "newPassword123", required = true)
    private String newPassword;

}
