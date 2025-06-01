package com.ptithcm.clinic_booking.exception;

import com.google.firebase.auth.FirebaseAuthException;
import com.ptithcm.clinic_booking.model.ApiResponse;
import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Xử lý lỗi không tìm thấy tài nguyên hoặc đối tượng yêu cầu (404 Not Found)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Xử lý lỗi không xác thực (401 Unauthorized)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<String>> handleUnauthorized(UnauthorizedException ex) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // Xử lý lỗi quyền truy cập bị từ chối, không có quyền truy cập (403 Forbidden)
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse<String>> handleForbidden(ForbiddenException ex) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.FORBIDDEN, ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    // Xử lý lỗi do người dùng hoặc yêu cầu không hợp lệ (400 Bad Request)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<String>> handleBadRequest(BadRequestException ex) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Xử lý lỗi do người dùng hoặc yêu cầu không hợp lệ (400 Bad Request)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationException(ValidationException ex) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Xử lý lỗi máy chủ (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<ApiResponse<String>> handleMailSendException(MailSendException ex) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Error when send email: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        ApiResponse<String> response = new ApiResponse<>(HttpStatus.BAD_REQUEST, errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> handleBadCredentials(BadCredentialsException ex) {
        ApiResponse response = new ApiResponse(HttpStatus.UNAUTHORIZED, "Username or password is incorrect");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalStateException(IllegalStateException ex) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<ApiResponse<String>> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException ex) {
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Data error: The query returned more than one result. Details: " + ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleFileNotFound(FileNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FirebaseAuthException.class)
    public ResponseEntity<ApiResponse<String>> handleFirebaseAuth(FirebaseAuthException ex) {
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.FORBIDDEN, "Permission Denied: " + ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiResponse<String>> handleFirebaseIOException(IOException ex) {
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Firebase Error: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FirebaseStorageException.class)
    public ResponseEntity<ApiResponse<String>> handleFirebaseStorageException(FirebaseStorageException ex) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
