package com.ptithcm.clinic_booking.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpEmailVerifyDTO {
    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    @Schema(description = "Email tài khoản", example = "doctor@example.com")
    private String email;

    @Size(min = 6, max = 6)
    @NotBlank(message = "OTP không được để trống")
    @Schema(description = "Email tài khoản", example = "999999")
    private String otp;
}
