package com.ptithcm.clinic_booking.dto.clinic;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClinicCreateDTO {
    @NotNull(message = "Tên phòng khám không được để trống.")
    @Size(min = 3, max = 100, message = "Tên phòng khám phải có độ dài từ 3 đến 100 ký tự")
    @Schema(description = "Tên phòng khám", example = "Phòng khám Đa khoa ABC")
    private String name = "Phòng khám Đa khoa ABC";

    @NotNull(message = "Địa chỉ không được để trống.")
    @Schema(description = "Địa chỉ phòng khám", example = "123 Đường Lê Lợi, Hà Nội")
    private String address = "123 Đường Lê Lợi, Hà Nội";

    @NotNull(message = "Số điện thoại không được để trống.")
    @Schema(description = "Số điện thoại liên hệ", example = "0987654321")
    private String phone = "0987654321";

    @Email(message = "Email không hợp lệ.")
    @Schema(description = "Email liên hệ", example = "contact@abcclinic.vn")
    private String email = "contact@abcclinic.vn";

    @Schema(description = "Mô tả phòng khám", example = "Phòng khám chuyên khoa đa dạng, phục vụ 24/7")
    private String description = "Phòng khám chuyên khoa đa dạng, phục vụ 24/7";

    @Schema(description = "Trạng thái phòng khám", example = "active")
    private String status = "ACTIVE";
}
