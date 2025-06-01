package com.ptithcm.clinic_booking.dto.manager;

import com.ptithcm.clinic_booking.dto.account.AccountRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerRequestDTO {

    @Schema(description = "Thông tin tài khoản")
    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 8, max = 50, message = "Tên đăng nhập phải từ 4 đến 50 ký tự")
    private AccountRequestDTO account;

    @Schema(description = "Tên quản lý", example = "Nguyễn Văn A", maxLength = 100)
    @NotBlank(message = "Tên không được để trống")
    @Size(max = 100, message = "Tên không được vượt quá 100 ký tự")
    private String name;

    @Schema(description = "Số điện thoại", example = "0912345678", pattern = "^(0|\\+84)[0-9]{9,10}$")
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0|\\+84)[0-9]{9,10}$", message = "Số điện thoại không hợp lệ")
    private String phone;

    @Schema(description = "Email", example = "nguyenvana@example.com", maxLength = 100)
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    @Size(max = 100, message = "Email không được vượt quá 100 ký tự")
    private String email;


    @Schema(description = "Địa chỉ", example = "123 Đường ABC, Quận 1, TP.HCM", maxLength = 255)
    @Size(max = 255, message = "Địa chỉ không được vượt quá 255 ký tự")
    private String address;

    @Schema(description = "Giới tính", example = "true")
    @NotNull(message = "Giới tính không được để trống")
    private Boolean gender;

    @Schema(description = "Trạng thái", example = "ACTIVE", allowableValues = {"ACTIVE", "BLOCKED", "DELETED"})
    @NotBlank(message = "Trạng thái không được để trống")
    @Pattern(regexp = "^(ACTIVE|BLOCKED|DELETED)$", message = "Trạng thái phải là ACTIVE, BLOCKED hoặc DELETED")
    private String status;

}
