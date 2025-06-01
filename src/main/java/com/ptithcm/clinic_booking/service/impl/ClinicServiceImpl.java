package com.ptithcm.clinic_booking.service.impl;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.clinic.ClinicDTO;
import com.ptithcm.clinic_booking.dto.clinic.ClinicCreateDTO;
import com.ptithcm.clinic_booking.dto.manager.ManagerResponseDTO;
import com.ptithcm.clinic_booking.mapper.ClinicMapper;
import com.ptithcm.clinic_booking.exception.ResourceNotFoundException;
import com.ptithcm.clinic_booking.mapper.ManagerMapper;
import com.ptithcm.clinic_booking.model.Clinic;
import com.ptithcm.clinic_booking.model.Manager;
import com.ptithcm.clinic_booking.repository.ClinicRepository;
import com.ptithcm.clinic_booking.service.ClinicService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicServiceImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public ClinicDTO getClinicById(String id){
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phòng khám với ID: " + id));
        return ClinicMapper.toClinicDTO(clinic);
    }

    public List<ClinicDTO> getAllClinics(){
        List<Clinic> clinics = clinicRepository.findAll();
        return clinics.stream()
                .map(ClinicMapper::toClinicDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClinicDTO> getAllActiveClinics() {
        List<Clinic> clinics = clinicRepository.findByStatus("ACTIVE");
        return clinics.stream()
                .map(ClinicMapper::toClinicDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PageResponse<ClinicDTO> getPageActiveClinics(PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();

        Page<Clinic> page = clinicRepository.findByStatus("ACTIVE",pageable);
        List<ClinicDTO> clinics = page.stream()
                .map(ClinicMapper::toClinicDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(clinics, page);
    }

    @Override
    public PageResponse<ClinicDTO> getPageClinics(PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<Clinic> page = clinicRepository.findAll(pageable);
        List<ClinicDTO> clinics = page.stream()
                .map(ClinicMapper::toClinicDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(clinics, page);
    }

    @Override
    public void addClinic(ClinicCreateDTO clinicDTO) {

        Clinic clinic = ClinicMapper.toClinic(clinicDTO);
        clinic.setId(createClinicId());
        clinicRepository.save(clinic);
    }

    private String createClinicId() {
        long countClinic = clinicRepository.count();
        return String.format("CL%04d", countClinic);
    }

    @Transactional
    @Override
    public void updateClinic(ClinicDTO clinicDTO) {
        clinicRepository.findById(clinicDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phòng khám với ID: " + clinicDTO.getId()));

        Clinic clinic = ClinicMapper.toClinic(clinicDTO);
        clinicRepository.save(clinic);
    }

    @Transactional
    @Override
    public void softDeleteClinic(String clinicId) {
        Clinic c = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new ResourceNotFoundException("Không thể xóa. Không tìm thấy phòng khám với ID: " + clinicId));
        c.setStatus("DELETED");
        clinicRepository.save(c);
    }
}
