package com.ptithcm.clinic_booking.controller.manager;

import com.ptithcm.clinic_booking.model.ApiResponse;
import com.ptithcm.clinic_booking.service.FirebaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/sh/upload-file")
@Validated
public class UploadFileController {
    private final FirebaseService firebaseService;

    public UploadFileController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<String>> addDoctorImage(@RequestParam("image") MultipartFile image){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, firebaseService.addImage(image)));
    }
}
