package com.ptithcm.clinic_booking.mapper;

import com.ptithcm.clinic_booking.dto.service.ServiceCreateDTO;
import com.ptithcm.clinic_booking.dto.service.ServiceDTO;
import com.ptithcm.clinic_booking.model.Manager;
import com.ptithcm.clinic_booking.model.MedicalSpecialty;
import com.ptithcm.clinic_booking.model.Service;

public class ServiceMapper {
    public static ServiceDTO toServiceDTO(Service service){
        if(service == null) return null;
        ServiceDTO dto = new ServiceDTO();
        dto.setId(service.getId());
//        dto.setCreatorId(service.getCreator().getId());
        dto.setMedicalSpecialty(MedicalSpecialtyMapper.toResponseDTO(service.getMedicalSpecialty()));
        dto.setName(service.getName());
        dto.setDescription(service.getDescription());
        dto.setStatus(service.getStatus());
        dto.setCreatedAt(service.getCreatedAt());

        return dto;
    }

    public static Service toService(ServiceDTO serviceDTO){
        if(serviceDTO == null) return null;
        Service service = new Service();
        service.setId(serviceDTO.getId());

        Manager creator = new Manager();
        service.setCreator(creator);

//        MedicalSpecialty specialty = new MedicalSpecialty();
//        specialty.setId();
//        service.setMedicalSpecialty(MedicalSpecialtyMapper.toEntity(serviceDTO.getMedicalSpecialtyResponseDTO()));

        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());
        service.setStatus(serviceDTO.getStatus());

        return service;
    }

    public static Service toService(ServiceCreateDTO serviceDTO){
        if(serviceDTO == null) return null;
        Service service = new Service();

        Manager creator = new Manager();
        creator.setId(serviceDTO.getCreatorId());
        service.setCreator(creator);

        MedicalSpecialty specialty = new MedicalSpecialty();
        specialty.setId(serviceDTO.getMedicalSpecialtyId());
        service.setMedicalSpecialty(specialty);

        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());
        service.setStatus(serviceDTO.getStatus());

        return service;
    }
}
