package com.ptithcm.clinic_booking.model;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "Trạng thái của lịch hẹn")
public enum AppointmentStatus {
    @Schema(description = "Cuộc hẹn đã được tạo và đang chờ xác nhận từ bác sĩ hoặc hệ thống")
    PENDING("Đang chờ xác nhận"),

    @Schema(description = "Cuộc hẹn đã được xác nhận bởi bác sĩ")
    CONFIRMED("Đã xác nhận"),

    @Schema(description = "Cuộc hẹn đang được tiến hành")
    ONGOING("Đang diễn ra"),

    @Schema(description = "Cuộc hẹn đã hoàn tất")
    COMPLETED("Đã hoàn tất"),

    @Schema(description = "Cuộc hẹn bị hủy bởi khách hàng hoặc bác sĩ")
    CANCELED("Đã hủy"),

    @Schema(description = "Cuộc hẹn bị bỏ lỡ (khách không đến)")
    NO_SHOW("Vắng mặt"),

    @Schema(description = "Cuộc hẹn đã bị xóa mềm khỏi hệ thống")
    DELETED("Đã xóa");

    private final String description;

    AppointmentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
