package com.ptithcm.clinic_booking.service;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.service.ServiceCreateDTO;
import com.ptithcm.clinic_booking.dto.service.ServiceDTO;

import java.util.List;

public interface OfferingService {
    ServiceDTO getServiceById(String id);
    List<ServiceDTO> getAllServices();
    PageResponse<ServiceDTO> getPageServices(PaginationRequest pageRequest);
    List<ServiceDTO> getAllActiveServices();
    PageResponse<ServiceDTO> getPageActiveServices(PaginationRequest pageRequest);
    void addService(ServiceCreateDTO serviceDTO);
    void updateService(ServiceDTO serviceDTO);
    void softDeleteService(String id);
}
