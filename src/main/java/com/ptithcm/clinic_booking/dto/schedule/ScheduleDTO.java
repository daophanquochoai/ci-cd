package com.ptithcm.clinic_booking.dto.schedule;

import com.ptithcm.clinic_booking.dto.clinic.ClinicDTO;
import com.ptithcm.clinic_booking.dto.doctor.DoctorSimpleResponseDTO;
import com.ptithcm.clinic_booking.model.ScheduleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    private int id;

    @NotNull(message = "Thông tin bác sĩ không được để trống")
    @Schema(description = "Thông tin bác sĩ")
    private DoctorSimpleResponseDTO doctor;

    @NotNull(message = "Thông tin phòng khám không được để trống")
    @Schema(description = "Thông tin phòng khám")
    private ClinicDTO clinic;

    @NotNull(message = "Ngày không được để trống")
    @FutureOrPresent(message = "Ngày phải là hôm nay hoặc trong tương lai")
    @Schema(description = "Ngày khám", example = "2025-06-01")
    private LocalDate date;

    @NotNull(message = "Thời gian bắt đầu không được để trống")
    @Schema(description = "Thời gian bắt đầu khám", example = "08:00:00")
    private LocalTime timeStart;

    @NotNull(message = "Thời gian kết thúc không được để trống")
    @Schema(description = "Thời gian kết thúc khám", example = "10:00:00")
    private LocalTime timeEnd;

    @NotNull(message = "Số lượt đặt tối đa không được để trống")
    @Min(value = 1, message = "Số lượt đặt tối thiểu là 1")
    @Schema(description = "Số lượt đặt tối đa", example = "10")
    private Short maxBooking;

    @Schema(description = "Trạng thái lịch khám", example = "UPCOMING")
    private ScheduleStatus status = ScheduleStatus.UPCOMING;

    @Schema(description = "Ngày tạo", example = "2025-05-22T10:00:00")
    private LocalDateTime createdAt;

}
