package com.ptithcm.clinic_booking.dto.specialty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class MedicalSpecialtyRequestDTO extends BaseMedicalSpecialtyDTO{
    @NotBlank(message = "Trạng thái không được để trống.")
    @Size(max = 15, message = "Trạng thái không được vượt quá 15 ký tự.")
    private String status;

    public MedicalSpecialtyRequestDTO() {
        super();
    }

    public MedicalSpecialtyRequestDTO(String id, String name, String description, String status) {
        super(id, name, description);
        this.status = status;
    }

}
