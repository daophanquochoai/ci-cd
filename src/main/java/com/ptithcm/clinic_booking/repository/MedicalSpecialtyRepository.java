package com.ptithcm.clinic_booking.repository;

import com.ptithcm.clinic_booking.model.MedicalSpecialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalSpecialtyRepository extends JpaRepository<MedicalSpecialty, String> {
    List<MedicalSpecialty> findByStatus(String status);
    Page<MedicalSpecialty> findByStatus(String status, Pageable pageable);
}
