package com.ptithcm.clinic_booking.service.impl;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.doctor.DoctorCreateDTO;
import com.ptithcm.clinic_booking.dto.doctor.DoctorSimpleResponseDTO;
import com.ptithcm.clinic_booking.dto.doctor.DoctorResponseDTO;
import com.ptithcm.clinic_booking.dto.manager.ManagerResponseDTO;
import com.ptithcm.clinic_booking.mapper.DoctorMapper;
import com.ptithcm.clinic_booking.exception.ResourceNotFoundException;
import com.ptithcm.clinic_booking.mapper.ManagerMapper;
import com.ptithcm.clinic_booking.model.Account;
import com.ptithcm.clinic_booking.model.Doctor;
import com.ptithcm.clinic_booking.model.Manager;
import com.ptithcm.clinic_booking.model.MedicalSpecialty;
import com.ptithcm.clinic_booking.repository.DoctorRepository;
import com.ptithcm.clinic_booking.service.AccountService;
import com.ptithcm.clinic_booking.service.DoctorService;
import com.ptithcm.clinic_booking.service.FirebaseService;
import com.ptithcm.clinic_booking.service.MedicalSpecialtyService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final AccountService accountService;
    private final FirebaseService firebaseService;
    private final MedicalSpecialtyService specialtyService;

    public DoctorServiceImpl(DoctorRepository doctorRepository, AccountService accountService,
                             FirebaseService firebaseService, MedicalSpecialtyService specialtyService) {
        this.doctorRepository = doctorRepository;
        this.accountService = accountService;
        this.firebaseService = firebaseService;
        this.specialtyService = specialtyService;
    }

    @Override
    public DoctorSimpleResponseDTO getDoctorSimpleById(String id) {
        Doctor  doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bác sĩ với ID: " + id));
        return DoctorMapper.toDoctorSimpleDTO(doctor);
    }

    @Override
    public DoctorResponseDTO getDoctorById(String id) {
        Doctor  doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bác sĩ với ID: " + id));
        return DoctorMapper.toDoctorDTO(doctor);
    }

    @Override
    public List<DoctorResponseDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();

        return doctors.stream()
                .map(DoctorMapper::toDoctorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorSimpleResponseDTO> getAllActiveDoctors() {
        List<Doctor> doctors = doctorRepository.findByStatus("ACTIVE");

        return doctors.stream()
                .map(DoctorMapper::toDoctorSimpleDTO)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<DoctorDTO> getDoctorsByService(String serviceId) {
//        List<Doctor> doctors = doctorRepository.findByService_Id(serviceId);
//        if(doctors == null) throw new ResourceNotFoundException("Không lấy được danh sách bác sĩ theo dịch vụ có id: "+serviceId);
//        return doctors.stream()
//                .map(DoctorMapper::toDoctorDTO)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<DoctorSimpleResponseDTO> getDoctorsByMedicalSpecialty(String medicalSpecialtyId) {
        List<Doctor> doctors = doctorRepository.findByMedicalSpecialty_Id(medicalSpecialtyId);

        return doctors.stream()
                .map(DoctorMapper::toDoctorSimpleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PageResponse<DoctorResponseDTO> getPageDoctors(PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();

        Page<Doctor> page = doctorRepository.findAll(pageable);
        List<DoctorResponseDTO> doctors = page.stream()
                .map(DoctorMapper::toDoctorDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(doctors, page);
    }

    @Transactional
    @Override
    public void addDoctor(DoctorCreateDTO doctorDTO) {
        Doctor doctor = DoctorMapper.toDoctor(doctorDTO);
        doctor.setId(createDoctorId());
        specialtyService.getMSpecialtyById(doctorDTO.getMedicalSpecialtyId());
        if(doctorDTO.getAccount().getRoleId()!=3) throw new IllegalArgumentException("Account role must be doctor (roleId = 3)");
        accountService.addAccount(doctorDTO.getAccount());

//        MultipartFile imageFile = doctorDTO.getImageFile();
//        if(imageFile!=null&& !imageFile.isEmpty()){
//            String imageUrl = addDoctorImage(imageFile, doctor.getId());
//            doctor.setImageLink(imageUrl);
//        }
        doctorRepository.save(doctor);
    }

    private String createDoctorId() {
        long countDoctor =  doctorRepository.count();
        return String.format("DC%03d", countDoctor);
    }

    private String addDoctorImage(MultipartFile imageFile, String fileName) {
        if (imageFile == null || imageFile.isEmpty())
            throw new IllegalArgumentException("Ảnh không được để trống.");


        String contentType = imageFile.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Chỉ chấp nhận file ảnh (jpeg, png, gif, ...).");
        }

        String originalFilename = imageFile.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        }
        fileName = "doctors/" + fileName + extension;

        try {
            firebaseService.uploadObject(fileName, imageFile.getInputStream(), contentType);
            return firebaseService.getObjectUrl(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PageResponse<DoctorSimpleResponseDTO> getPageActiveDoctors(PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();

        Page<Doctor> page = doctorRepository.findByStatus("ACTIVE", pageable);
        List<DoctorSimpleResponseDTO> doctors = page.stream()
                .map(DoctorMapper::toDoctorSimpleDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(doctors, page);
    }

    @Transactional
    @Override
    public void updateDoctor(DoctorSimpleResponseDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById( doctorDTO.getId())
           .orElseThrow(() ->new ResourceNotFoundException("Không tìm thấy phòng khám với ID: " +  doctorDTO.getId()));
//            Doctor doctor = DoctorMapper.toDoctor(doctorDTO);
            MedicalSpecialty specialty = new MedicalSpecialty();
            specialty.setId(doctorDTO.getMedicalSpecialtyId());
            doctor.setMedicalSpecialty(specialty);
            doctor.setName(doctorDTO.getName());
            doctor.setPhone(doctorDTO.getPhone());
            doctor.setEmail(doctorDTO.getEmail());
            doctor.setAddress(doctorDTO.getAddress());
            doctor.setGender(doctorDTO.getGender());
            doctorRepository.save(doctor);
    }

    @Transactional
    @Override
    public void uploadDoctorImage(String doctorId, MultipartFile imageFile)  {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bác sĩ với ID: " + doctorId));
//        firebaseService.uploadObject(imageFile.getName(),imageFile.getInputStream(), imageFile.getContentType());
//        String imageLink = firebaseService.getObjectUrl(imageFile.getName());
        String imageLink = addDoctorImage(imageFile, doctorId);
        doctor.setImageLink(imageLink);
        doctorRepository.save(doctor);
    }

    @Transactional
    @Override
    public void blockDoctor(String id) {
            Doctor d = doctorRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Không thể xóa. Không tìm thấy phòng khám với ID: " + id));
            d.setStatus("BLOCKED");

            doctorRepository.save(d);
    }

    @Transactional
    @Override
    public void softDeletingDoctor(String id) {
        Doctor d = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không thể xóa. Không tìm thấy phòng khám với ID: " + id));

        accountService.softDeleteAccount(d.getAccount().getUsername());
        d.setStatus("DELETED");
        doctorRepository.save(d);
    }
}
