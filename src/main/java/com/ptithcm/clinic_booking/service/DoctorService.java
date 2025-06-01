package com.ptithcm.clinic_booking.service;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.doctor.DoctorCreateDTO;
import com.ptithcm.clinic_booking.dto.doctor.DoctorSimpleResponseDTO;
import com.ptithcm.clinic_booking.dto.doctor.DoctorResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface DoctorService {
    DoctorSimpleResponseDTO getDoctorSimpleById(String id);
    DoctorResponseDTO getDoctorById(String id);
    List<DoctorResponseDTO> getAllDoctors();
    List<DoctorSimpleResponseDTO> getAllActiveDoctors();
//    List<DoctorDTO> getDoctorsByService(String serviceId);
    List<DoctorSimpleResponseDTO> getDoctorsByMedicalSpecialty(String medicalSpecialtyId);

    void addDoctor(DoctorCreateDTO doctorDTO);
    void updateDoctor(DoctorSimpleResponseDTO doctor);
    void blockDoctor(String id);
    void softDeletingDoctor(String id);

    PageResponse<DoctorResponseDTO> getPageDoctors(PaginationRequest pageRequest);

    PageResponse<DoctorSimpleResponseDTO> getPageActiveDoctors(PaginationRequest pageRequest);
    void uploadDoctorImage(String doctorId, MultipartFile imageFile);
}
