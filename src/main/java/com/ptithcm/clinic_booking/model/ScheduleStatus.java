package com.ptithcm.clinic_booking.model;

import io.swagger.v3.oas.annotations.media.Schema;

public enum ScheduleStatus {

    @Schema(description = "Lịch trình đã được tạo và đang chờ xác nhận từ hệ thống")
    PENDING("Đang chờ xác nhận"),

    @Schema(description = "Lịch trình làm việc đã được tạo")
    ACTIVE("Đã xác nhận"),

    @Schema(description = "Lịch trình làm việc sắp tới, cho phép đặt nếu trống")
    UPCOMING("Sắp diễn ra"),

    @Schema(description = "Lịch trình làm việc đang diễn ra, cho phép đặt nếu trống")
    ONGOING("Đang diễn ra"),

    @Schema(description = "Lịch trình làm việc đã tạm dừng (nghỉ phép đột xuất)")
    PAUSED("Tạm dừng, nghỉ phép đột xuất"),

    @Schema(description = "Lịch trình làm việc đã quá hạn")
    EXPIRED("Đã quá hạn"),

    @Schema(description = "Lịch trình làm việc bị hủy bởi bác sĩ")
    CANCELED("Đã hủy"),

    @Schema(description = "Lịch trình làm việc bị xóa")
    DELETED("Đã xóa");

    private String label;

    ScheduleStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
