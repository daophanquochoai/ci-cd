package com.ptithcm.clinic_booking.model;

import com.ptithcm.clinic_booking.exception.MessageConstants;
import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(HttpStatus httpStatus, T data) {
        this.statusCode = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
//Mã	Ý nghĩa
//200	OK – Thành công
//201	Created – Tạo mới thành công
//400	Bad Request – Lỗi đầu vào
//401	Unauthorized – Không xác thực
//403	Forbidden – Không có quyền
//404	Not Found – Không tìm thấy
//500	Internal Server Error – Lỗi server