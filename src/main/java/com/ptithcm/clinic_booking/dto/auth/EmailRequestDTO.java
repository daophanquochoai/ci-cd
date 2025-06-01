package com.ptithcm.clinic_booking.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestDTO {
    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    @Schema(description = "Email tài khoản", example = "doctor@example.com")
    private String email;

    public @Email(message = "Email không hợp lệ") @NotBlank(message = "Email không được để trống") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email không hợp lệ") @NotBlank(message = "Email không được để trống") String email) {
        this.email = email;
    }
}