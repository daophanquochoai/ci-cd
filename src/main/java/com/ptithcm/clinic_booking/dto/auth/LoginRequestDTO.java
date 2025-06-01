package com.ptithcm.clinic_booking.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "Tên đăng nhập không được để trống.")
    @Size(min = 6, max = 50, message = "Tên tài khoản phải có ít nhất 6 ký tự.")
    @Schema(description = "Tên đăng nhập", example = "username")
    private String username;
    @NotBlank(message = "Mật khẩu không được để trống.")
    @Size(min = 8, max = 255, message = "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ thường, chữ hoa và số.")
    @Schema(description = "Mật khẩu", example = "12345678")
    private String password;
}
