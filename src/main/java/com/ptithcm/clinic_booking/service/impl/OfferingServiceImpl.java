package com.ptithcm.clinic_booking.service.impl;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.service.ServiceCreateDTO;
import com.ptithcm.clinic_booking.dto.service.ServiceDTO;
import com.ptithcm.clinic_booking.mapper.ServiceMapper;
import com.ptithcm.clinic_booking.exception.ResourceNotFoundException;
import com.ptithcm.clinic_booking.model.Service;
import com.ptithcm.clinic_booking.repository.ServiceRepository;
import com.ptithcm.clinic_booking.service.OfferingService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class OfferingServiceImpl implements OfferingService {

    private final ServiceRepository serviceRepository;

    public OfferingServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServiceDTO getServiceById(String id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy dịch vụ có id: "+ id));

        return ServiceMapper.toServiceDTO(service);
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        List<Service> services = serviceRepository.findAll();
        return services.stream()
                .map(ServiceMapper::toServiceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PageResponse<ServiceDTO> getPageServices(PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<Service> page = serviceRepository.findAll(pageable);
        List<ServiceDTO> services = page.getContent().stream()
                .map(ServiceMapper::toServiceDTO)
                .toList();
        return new PageResponse<>(services, page);
    }

    @Override
    public PageResponse<ServiceDTO> getPageActiveServices(PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<Service> page = serviceRepository.findAllByStatus("ACTIVE", pageable);

        List<ServiceDTO> services = page.getContent().stream()
                .map(ServiceMapper::toServiceDTO)
                .toList();
        return new PageResponse<>(services, page);
    }

    @Override
    public List<ServiceDTO> getAllActiveServices() {
        List<Service> services = serviceRepository.findAllByStatus("ACTIVE");
        if(services == null)
            throw new ResourceNotFoundException("Không thể lấy được danh sách dịch vụ.");
        return services.stream()
                .map(ServiceMapper::toServiceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addService(ServiceCreateDTO serviceDTO) {
        Service service = ServiceMapper.toService(serviceDTO);
        service.setId(createdServiceId());
        serviceRepository.save(service);

    }

    private String createdServiceId() {
        long countService =serviceRepository.count();
        return String.format("SV%05d", countService + 1);
    }

    @Transactional
    @Override
    public void updateService(ServiceDTO serviceDTO) {
        Service existingService = serviceRepository.findById( serviceDTO.getId()).orElse(null);
        if(existingService == null)
            throw  new ResourceNotFoundException("Không tìm thấy dịch vụ với ID: " + serviceDTO.getId());

        Service service = ServiceMapper.toService(serviceDTO);
        serviceRepository.save(service);

    }

    @Transactional
    @Override
    public void softDeleteService(String id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy dịch vụ có id: "+ id));

            service.setStatus("DELETED");
            serviceRepository.save(service);
    }
}
