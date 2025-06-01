package com.ptithcm.clinic_booking.mapper;

import com.ptithcm.clinic_booking.dto.clinic.ClinicDTO;
import com.ptithcm.clinic_booking.dto.clinic.ClinicCreateDTO;
import com.ptithcm.clinic_booking.model.Clinic;

public class ClinicMapper {
    public static ClinicDTO toClinicDTO(Clinic clinic){
        if( clinic == null) return null;
        ClinicDTO c = ClinicDTO.builder()
                .id(clinic.getId())
                .name(clinic.getName())
                .address(clinic.getAddress())
                .email(clinic.getEmail())
                .phone(clinic.getPhone())
                .description(clinic.getDescription())
                .status(clinic.getStatus())
                .createdAt(clinic.getCreatedAt())
                .build();
        return c;
    }

    public static Clinic toClinic(ClinicDTO clinicDTO){
        if(clinicDTO == null) return null;
        Clinic c = new Clinic();
        c.setId(clinicDTO.getId() != null ? clinicDTO.getId().toString() : null);
        c.setName(clinicDTO.getName());
        c.setAddress(clinicDTO.getAddress());
        c.setDescription(clinicDTO.getDescription());
        c.setPhone(clinicDTO.getPhone());
        c.setEmail(clinicDTO.getEmail());
        c.setStatus(clinicDTO.getStatus());
        c.setCreatedAt(clinicDTO.getCreatedAt());
        return c;
    }

    public static Clinic toClinic(ClinicCreateDTO clinicDTO){
        if(clinicDTO == null) return null;
        Clinic c = new Clinic();
        c.setName(clinicDTO.getName());
        c.setAddress(clinicDTO.getAddress());
        c.setDescription(clinicDTO.getDescription());
        c.setPhone(clinicDTO.getPhone());
        c.setEmail(clinicDTO.getEmail());
        c.setStatus(clinicDTO.getStatus());
//        c.setCreatedAt(clinicDTO.getCreatedAt());
        return c;
    }
}
