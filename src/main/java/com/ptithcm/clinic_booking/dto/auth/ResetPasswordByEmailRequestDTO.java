package com.ptithcm.clinic_booking.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ResetPasswordByEmailRequestDTO {
    @NotBlank(message = "Token không được để trống")
    @Schema(description = "Token người dùng", example = "edjasoduweqjweqew....")
    private String token;

    @NotBlank(message = "Mật khẩu mới không được để trống")
    @Size(min = 8, max = 100, message = "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ thường, chữ hoa và số.")
    @Schema(description = "Mật khẩu mới của người dùng", example = "newPassword123")
    private String newPassword;
}
