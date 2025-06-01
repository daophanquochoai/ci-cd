package com.ptithcm.clinic_booking.dto.specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class MedicalSpecialtyResponseDTO extends BaseMedicalSpecialtyDTO{
    private String status;
    private LocalDateTime createdAt;

    public MedicalSpecialtyResponseDTO() {
        super();
    }

    public MedicalSpecialtyResponseDTO(String id, String name, String description, String status, LocalDateTime createdAt) {
        super(id, name, description);
        this.status = status;
        this.createdAt = createdAt;
    }
}
