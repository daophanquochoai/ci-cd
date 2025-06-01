package com.ptithcm.clinic_booking.service;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.clinic.ClinicDTO;
import com.ptithcm.clinic_booking.dto.clinic.ClinicCreateDTO;

import java.util.List;

public interface ClinicService {
    ClinicDTO getClinicById(String id);
    List<ClinicDTO> getAllClinics();
    List<ClinicDTO> getAllActiveClinics();

    void addClinic(ClinicCreateDTO clinicDTO);
    void updateClinic(ClinicDTO clinicDTO);
    void softDeleteClinic(String clinicId);

    PageResponse<ClinicDTO> getPageActiveClinics(PaginationRequest pageRequest);
    PageResponse<ClinicDTO> getPageClinics(PaginationRequest pageRequest);
}
