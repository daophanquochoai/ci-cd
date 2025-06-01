package com.ptithcm.clinic_booking.service.impl;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.manager.ManagerResponseDTO;
import com.ptithcm.clinic_booking.dto.specialty.BaseMedicalSpecialtyDTO;
import com.ptithcm.clinic_booking.dto.specialty.MedicalSpecialtyRequestDTO;
import com.ptithcm.clinic_booking.dto.specialty.MedicalSpecialtyResponseDTO;
import com.ptithcm.clinic_booking.exception.ResourceNotFoundException;
import com.ptithcm.clinic_booking.mapper.ManagerMapper;
import com.ptithcm.clinic_booking.mapper.MedicalSpecialtyMapper;
import com.ptithcm.clinic_booking.model.Manager;
import com.ptithcm.clinic_booking.model.MedicalSpecialty;
import com.ptithcm.clinic_booking.repository.MedicalSpecialtyRepository;
import com.ptithcm.clinic_booking.service.MedicalSpecialtyService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalSpecialtyServiceImpl implements MedicalSpecialtyService {
    private final MedicalSpecialtyRepository specialtyRepository;

    public MedicalSpecialtyServiceImpl(MedicalSpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public List<MedicalSpecialtyResponseDTO> getAllMSpecialties() {
        List<MedicalSpecialty> medicalSpecialties = specialtyRepository.findAll();

        return medicalSpecialties .stream()
                .map(MedicalSpecialtyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalSpecialtyResponseDTO getMSpecialtyById(String id) {
        MedicalSpecialty medicalSpecialty = specialtyRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Không tìm thấy chuyên khoa với ID: " + id));
        return MedicalSpecialtyMapper.toResponseDTO(medicalSpecialty);
    }

    @Override
    public PageResponse<MedicalSpecialtyResponseDTO> getPageMSpecialties(PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();

        Page<MedicalSpecialty> page = specialtyRepository.findAll(pageable);
        List<MedicalSpecialtyResponseDTO> medicalSpecialties = page.stream()
                .map(MedicalSpecialtyMapper::toResponseDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(medicalSpecialties, page);
    }

    @Override
    public void addMSpecialty(MedicalSpecialtyRequestDTO specialtyRequestDTO) {
        MedicalSpecialty specialty = MedicalSpecialtyMapper.toEntity(specialtyRequestDTO);
        specialty.setId(createSpecialtyId());
        specialtyRepository.save(specialty);
    }

    private String createSpecialtyId() {
        long countClinic = specialtyRepository.count();
        return String.format("CL%04d", countClinic);
    }

    @Transactional
    @Override
    public void updateMSpecialty(MedicalSpecialtyRequestDTO specialtyRequestDTO) {

        MedicalSpecialty existingSpecialty = specialtyRepository.findById(specialtyRequestDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chuyên khoa với ID: " + specialtyRequestDTO.getId()));

        existingSpecialty.setName(specialtyRequestDTO.getName());
        existingSpecialty.setStatus(specialtyRequestDTO.getStatus());
        existingSpecialty.setDescription(specialtyRequestDTO.getDescription());

        specialtyRepository.save(existingSpecialty);
    }

    @Transactional
    @Override
    public void softDeleteMSpecialty(String specialtyId) {

        MedicalSpecialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new ResourceNotFoundException("Không thể xóa. Không tìm thấy chuyên khoa với ID: " + specialtyId));

        specialty.setStatus("DELETED");
        specialtyRepository.save(specialty);
    }

    @Override
    public List<BaseMedicalSpecialtyDTO> getAllActiveMSpecialties() {
        List<MedicalSpecialty> activeSpecialties = specialtyRepository.findByStatus("ACTIVE");
        return activeSpecialties.stream()
                .map(MedicalSpecialtyMapper::toBaseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PageResponse<BaseMedicalSpecialtyDTO> getPageActiveMSpecialties(PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();

        Page<MedicalSpecialty> page = specialtyRepository.findByStatus("ACTIVE", pageable);
        List<BaseMedicalSpecialtyDTO> medicalSpecialties = page.stream()
                .map(MedicalSpecialtyMapper::toResponseDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(medicalSpecialties, page);
    }
}
