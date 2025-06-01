package com.ptithcm.clinic_booking.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
public class CustomerRequestDTO {
    @NotBlank(message = "Tên khách hàng không được để trống.")
    @Size(max = 100, message = "Tên khách hàng không được vượt quá 100 ký tự.")
    private String name;

    @NotBlank(message = "Số điện thoại không được để trống.")
    @Size(max = 15, message = "Số điện thoại không được vượt quá 15 ký tự.")
    private String phone;

    @NotBlank(message = "Email không được để trống.")
    @Email(message = "Email không hợp lệ.")
    @Size(max = 100, message = "Email không được vượt quá 100 ký tự.")
    @Schema(description = "Email liên hệ", example = "nguyenvana3@example.com")
    private String email;

    @Size(max = 255, message = "Địa chỉ không được vượt quá 255 ký tự.")
    @Schema(description = "Địa chỉ liên hệ", example = "123 Đường ABC, Quận 1, TP.HCM")
    private String address;
    @Schema(description = "Giới tính", example = "true")
    private Boolean gender;
}
