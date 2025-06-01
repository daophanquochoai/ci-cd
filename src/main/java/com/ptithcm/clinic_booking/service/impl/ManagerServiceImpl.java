package com.ptithcm.clinic_booking.service.impl;

import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.manager.ManagerRequestDTO;
import com.ptithcm.clinic_booking.dto.manager.ManagerResponseDTO;
import com.ptithcm.clinic_booking.exception.ResourceNotFoundException;
import com.ptithcm.clinic_booking.mapper.ManagerMapper;
import com.ptithcm.clinic_booking.model.Manager;
import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.repository.ManagerRepository;
import com.ptithcm.clinic_booking.service.AccountService;
import com.ptithcm.clinic_booking.service.ManagerService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final AccountService accountService;
    public ManagerServiceImpl(ManagerRepository managerRepository, AccountService accountService) {
        this.managerRepository = managerRepository;
        this.accountService = accountService;
    }

    @Transactional
    @Override
    public void addManager(ManagerRequestDTO managerRequestDTO) {
        Manager manager = ManagerMapper.toManager(managerRequestDTO);
        manager.setId(createManagerId());
        if(managerRequestDTO.getAccount().getRoleId()!=2) throw new IllegalArgumentException("Account role must be manager (roleId = 2)");
        accountService.addAccount(managerRequestDTO.getAccount());

        managerRepository.save(manager);
    }

    private String createManagerId() {
        long count = managerRepository.count();
        return String.format("MN%03d", count + 1);
    }

    @Transactional
    @Override
    public void updateManager(ManagerResponseDTO managerResponseDTO) {
        Manager existing = managerRepository.findById(managerResponseDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy quản lý với ID: " + managerResponseDTO.getId()));

        Manager updated = ManagerMapper.toManager(managerResponseDTO);
        updated.setStatus(existing.getStatus()); // giữ nguyên trạng thái
        managerRepository.save(updated);
    }

    @Transactional
    @Override
    public void softDeleteManager(String id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy quản lý với ID: " + id));

        manager.setStatus("DELETED");
        managerRepository.save(manager);
    }

    @Override
    public List<ManagerResponseDTO> getAllManagers() {
        List<Manager> managers = managerRepository.findAll();
        return managers.stream()
                .map(ManagerMapper::toManagerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PageResponse<ManagerResponseDTO> getPageManagers(PaginationRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();

        Page<Manager> page = managerRepository.findAll(pageable);
        List<ManagerResponseDTO> managers = page.stream()
                .map(ManagerMapper::toManagerDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(managers, page);
    }

    @Override
    public ManagerResponseDTO getManagerById(String id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy quản lý với ID: " + id));
        return ManagerMapper.toManagerDTO(manager);
    }
}