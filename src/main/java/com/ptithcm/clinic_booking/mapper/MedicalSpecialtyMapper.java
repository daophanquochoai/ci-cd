package com.ptithcm.clinic_booking.mapper;

import com.ptithcm.clinic_booking.dto.specialty.BaseMedicalSpecialtyDTO;
import com.ptithcm.clinic_booking.dto.specialty.MedicalSpecialtyRequestDTO;
import com.ptithcm.clinic_booking.dto.specialty.MedicalSpecialtyResponseDTO;
import com.ptithcm.clinic_booking.model.MedicalSpecialty;

public class MedicalSpecialtyMapper {
    public static MedicalSpecialtyResponseDTO toResponseDTO(MedicalSpecialty specialty){
        if(specialty == null) return null;
        MedicalSpecialtyResponseDTO specialtyResponseDTO = new MedicalSpecialtyResponseDTO();
        specialtyResponseDTO.setId(specialty.getId());
        specialtyResponseDTO.setName(specialty.getName());
        specialtyResponseDTO.setDescription(specialty.getDescription());
        specialtyResponseDTO.setStatus(specialty.getStatus());
        specialtyResponseDTO.setCreatedAt(specialty.getCreatedAt());
        return specialtyResponseDTO;
    }

    public static MedicalSpecialty toEntity(MedicalSpecialtyRequestDTO specialtyRequestDTO) {
        if (specialtyRequestDTO == null) return null;

        MedicalSpecialty specialty = new MedicalSpecialty();
        specialty.setId(specialtyRequestDTO.getId());
        specialty.setName(specialtyRequestDTO.getName());
        specialty.setDescription(specialtyRequestDTO.getDescription());
        specialty.setStatus(specialtyRequestDTO.getStatus());
        return specialty;
    }

    public static BaseMedicalSpecialtyDTO toBaseDTO(MedicalSpecialty specialty) {
        if (specialty == null) return null;

        BaseMedicalSpecialtyDTO dto = new BaseMedicalSpecialtyDTO();
        dto.setId(specialty.getId());
        dto.setName(specialty.getName());
        dto.setDescription(specialty.getDescription());
        return dto;
    }
}
