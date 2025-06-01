package com.ptithcm.clinic_booking.controller.manager;

import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.manager.ManagerRequestDTO;
import com.ptithcm.clinic_booking.dto.manager.ManagerResponseDTO;
import com.ptithcm.clinic_booking.model.ApiResponse;
import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/m/managers")
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ManagerResponseDTO>> getManagerById(@PathVariable String id) {
        ManagerResponseDTO manager = managerService.getManagerById(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, manager));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ManagerResponseDTO>>> getAllManagers() {
        List<ManagerResponseDTO> managers = managerService.getAllManagers();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, managers));
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<PageResponse<ManagerResponseDTO>>> getPageManagers(@ModelAttribute @Valid PaginationRequest page) {
        PageResponse<ManagerResponseDTO> managers = managerService.getPageManagers(page);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, managers));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addManager(@RequestBody @Valid ManagerRequestDTO manager){
        managerService.addManager(manager);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED, "Thêm thành công"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateManager(@RequestBody  @Valid ManagerResponseDTO manager){
        managerService.updateManager(manager);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "Cập nhật thành công"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> softDeleteManager(@PathVariable String id){
        managerService.softDeleteManager(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,"Xóa thành công"));
    }
}
