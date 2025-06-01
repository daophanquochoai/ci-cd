package com.ptithcm.clinic_booking.service;

import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.manager.ManagerRequestDTO;
import com.ptithcm.clinic_booking.dto.manager.ManagerResponseDTO;
import com.ptithcm.clinic_booking.dto.PageResponse;

import java.util.List;

public interface ManagerService {
    void addManager(ManagerRequestDTO managerRequestDTO);
    void updateManager(ManagerResponseDTO managerResponseDTO);
    void softDeleteManager(String id);

    List<ManagerResponseDTO> getAllManagers();
    PageResponse<ManagerResponseDTO> getPageManagers(PaginationRequest pageable);
    ManagerResponseDTO getManagerById(String id);
}
