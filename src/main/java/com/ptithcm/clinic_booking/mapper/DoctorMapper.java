package com.ptithcm.clinic_booking.mapper;

import com.ptithcm.clinic_booking.dto.doctor.DoctorCreateDTO;
import com.ptithcm.clinic_booking.dto.doctor.DoctorSimpleResponseDTO;
import com.ptithcm.clinic_booking.dto.doctor.DoctorResponseDTO;
import com.ptithcm.clinic_booking.model.Doctor;
import com.ptithcm.clinic_booking.model.MedicalSpecialty;

public class DoctorMapper {

    public static DoctorSimpleResponseDTO toDoctorSimpleDTO(Doctor doctor){
        if(doctor == null) return null;
        DoctorSimpleResponseDTO d = new DoctorSimpleResponseDTO();
        d.setId(doctor.getId());
        d.setMedicalSpecialtyId(doctor.getMedicalSpecialty().getId());
        d.setName(doctor.getName());
        d.setPhone(doctor.getPhone());
        d.setEmail(doctor.getEmail());
        d.setAddress(doctor.getAddress());
        d.setGender(doctor.getGender());
        d.setImageLink(doctor.getImageLink());
        d.setCreatedAt(doctor.getCreatedAt());
        d.setBirthday(doctor.getBirthday());
        d.setDescription(doctor.getDescription());
        d.setQualification(doctor.getQualification());
        return d;
    }
    public static DoctorResponseDTO toDoctorDTO(Doctor doctor){
        if(doctor == null) return null;
        DoctorResponseDTO d = new DoctorResponseDTO();
        d.setId(doctor.getId());
        d.setAccount(AccountMapper.toAccountResponseDTO(doctor.getAccount()));
        d.setMedicalSpecialtyId(doctor.getMedicalSpecialty().getId());
        d.setName(doctor.getName());
        d.setPhone(doctor.getPhone());
        d.setEmail(doctor.getEmail());
        d.setAddress(doctor.getAddress());
        d.setGender(doctor.getGender());
        d.setImageLink(doctor.getImageLink());
        d.setStatus(doctor.getStatus());
        d.setCreatedAt(doctor.getCreatedAt());
        d.setBirthday(doctor.getBirthday());
        d.setDescription(doctor.getDescription());
        d.setQualification(doctor.getQualification());
        return d;
    }

    public static Doctor toDoctor(DoctorCreateDTO doctorDTO){
        if(doctorDTO == null) return null;
        Doctor d = new Doctor();
//        d.setId(doctorDTO.getId());
        d.setAccount(AccountMapper.toAccount(doctorDTO.getAccount()));

        MedicalSpecialty specialty = new MedicalSpecialty();
        specialty.setId(doctorDTO.getMedicalSpecialtyId());
        d.setMedicalSpecialty(specialty);

        d.setName(doctorDTO.getName());
        d.setPhone(doctorDTO.getPhone());
        d.setEmail(doctorDTO.getEmail());
        d.setAddress(doctorDTO.getAddress());
        d.setGender(doctorDTO.getGender());
        d.setStatus(doctorDTO.getStatus());
        d.setBirthday(doctorDTO.getBirthday());
        d.setDescription(doctorDTO.getDescription());
        d.setQualification(doctorDTO.getQualification());
        return d;
    }

    public static Doctor toDoctor(DoctorSimpleResponseDTO doctorDTO){
        if(doctorDTO == null) return null;
        Doctor d = new Doctor();
        d.setId(doctorDTO.getId());
        MedicalSpecialty specialty = new MedicalSpecialty();
        specialty.setId(doctorDTO.getMedicalSpecialtyId());
        d.setMedicalSpecialty(specialty);
        d.setImageLink(doctorDTO.getImageLink());
        d.setName(doctorDTO.getName());
        d.setPhone(doctorDTO.getPhone());
        d.setEmail(doctorDTO.getEmail());
        d.setAddress(doctorDTO.getAddress());
        d.setGender(doctorDTO.getGender());
        return d;
    }
}
