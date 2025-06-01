package com.ptithcm.clinic_booking.dto.specialty;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class BaseMedicalSpecialtyDTO {
    @Schema(description = "Mã chuyên khoa", example = "MS01")
    private String id;

    @NotNull(message = "Tên chuyên khoa không được để trống.")
    @Size(max = 100, message = "Tên chuyên khoa không được vượt quá 100 ký tự.")
    private String name;
    private String description;
}
