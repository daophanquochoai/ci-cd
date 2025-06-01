package com.ptithcm.clinic_booking.dto.appointment;

import com.ptithcm.clinic_booking.dto.service.ServiceDTO;
import com.ptithcm.clinic_booking.dto.customer.CustomerDTO;
import com.ptithcm.clinic_booking.dto.schedule.ScheduleDTO;
import com.ptithcm.clinic_booking.model.AppointmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    @Schema(description = "Id cuộc hẹn", example = "10001")
    private Integer id;

    @NotNull(message = "Dịch vụ không được để trống.")
    @Schema(description = "Thông tin dịch vụ")
    private ServiceDTO service;

    @NotNull(message = "Lịch hẹn không được để trống.")
    @Schema(description = "Thông tin lịch trình")
    private ScheduleDTO schedule;

    @NotNull(message = "Khách hàng không được để trống.")
    @Schema(description = "Thông tin khách hàng")
    private CustomerDTO customer;

    @NotNull(message = "Số thứ tự không được để trống.")
    @Min(value = 1, message = "Số thứ tự phải lớn hơn hoặc bằng 1.")
    @Schema(description = "Số thứ tự trong danh sách", example = "1")
    private Short numericalOrder;

    @NotBlank(message = "Ghi chú không được để trống.")
    @Size(max = 255, message = "Ghi chú không được vượt quá 255 ký tự.")
    @Schema(description = "Ghi chú cho lịch hẹn", example = "Khách hàng đến đúng giờ")
    private String note;

    @NotBlank(message = "Trạng thái không được để trống.")
    @Size(max = 15, message = "Trạng thái không được vượt quá 15 ký tự.")
    @Schema(description = "Trạng thái lịch hẹn", example = "PENDING")
    private AppointmentStatus status;

    @Schema(description = "Thời gian cập nhật", example = "2025-05-22T15:30:00")
    private LocalDateTime updatedAt;

    @Schema(description = "Người cập nhật", example = "admin")
    private String updatedBy;

    @Schema(description = "Thời gian tạo", example = "2025-05-20T10:00:00")
    private LocalDateTime createdAt;

}
