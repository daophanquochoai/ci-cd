package com.ptithcm.clinic_booking.controller;

import com.ptithcm.clinic_booking.dto.PageResponse;
import com.ptithcm.clinic_booking.dto.PaginationRequest;
import com.ptithcm.clinic_booking.dto.service.ServiceDTO;
import com.ptithcm.clinic_booking.model.ApiResponse;
import com.ptithcm.clinic_booking.service.OfferingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/p/services")
@Validated
public class OfferingController {
    private final OfferingService offeringService;

    @Autowired
    public OfferingController(OfferingService offeringService) {
        this.offeringService = offeringService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceDTO>> getServiceById(@PathVariable  @NotBlank String id){
        ServiceDTO service = offeringService.getServiceById(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, service));
    }

    @GetMapping("/active/all")
    public ResponseEntity<ApiResponse<List<ServiceDTO>>> getAllActiveServices() {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, offeringService.getAllActiveServices()));
    }

    @GetMapping("/active/page")
    public ResponseEntity<ApiResponse<PageResponse<ServiceDTO>>> getPageActiveServices(@ModelAttribute @Valid PaginationRequest pageRequest) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, offeringService.getPageActiveServices(pageRequest)));
    }
}
