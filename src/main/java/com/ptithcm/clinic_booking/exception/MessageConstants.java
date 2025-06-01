package com.ptithcm.clinic_booking.exception;

public class MessageConstants {
    // Thành công
    public static final String SUCCESS = "Success";
    public static final String SUCCESS_CREATED = "Resource successfully created";
    public static final String SUCCESS_UPDATED = "Resource successfully updated";
    public static final String SUCCESS_DELETED = "Resource successfully deleted";
    // Lỗi 400 - Lỗi người dùng yêu cầu không hợp lệ (Bad Request)
    public static final String VALIDATION_ERROR = "Validation error";
    public static final String INVALID_REQUEST = "Invalid request";
    public static final String MISSING_REQUIRED_FIELDS = "Missing required fields";
    // Lỗi 404 - Không tìm thấy
    public static final String NOT_FOUND = "Not found";
    public static final String RESOURCE_NOT_FOUND = "Requested resource was not found";
    // Lỗi 500 - Lỗi máy chủ nội bộ
    public static final String INTERNAL_ERROR = "Internal server error";
    public static final String DATABASE_ERROR = "Database connection error";
    public static final String UNKNOWN_ERROR = "An unknown error occurred";
    // Lỗi xác thực
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String FORBIDDEN = "Forbidden";
    // Lỗi người dùng chưa đăng nhập
    public static final String USER_NOT_AUTHENTICATED = "User not authenticated";
    // Lỗi dữ liệu không hợp lệ
    public static final String INVALID_DATA = "Provided data is invalid";

    public enum HttpStatusCode {
        SUCCESS(200, MessageConstants.SUCCESS),
        CREATED(201, MessageConstants.SUCCESS_CREATED),
        BAD_REQUEST(400, MessageConstants.VALIDATION_ERROR),
        NOT_FOUND(404, MessageConstants.RESOURCE_NOT_FOUND),
        INTERNAL_SERVER_ERROR(500, MessageConstants.INTERNAL_ERROR),
        UNAUTHORIZED(401, MessageConstants.UNAUTHORIZED),
        FORBIDDEN(403, MessageConstants.FORBIDDEN);
        private final int statusCode;
        private final String message;
        HttpStatusCode(int statusCode, String message) {
            this.statusCode = statusCode;
            this.message = message;
        }
        public int getStatusCode() {
            return statusCode;
        }

        public String getMessage() {
            return message;
        }
    }
}
