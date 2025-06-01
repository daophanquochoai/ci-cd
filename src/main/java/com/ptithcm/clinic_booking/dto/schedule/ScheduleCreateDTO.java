package com.ptithcm.clinic_booking.dto.schedule;

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
import java.time.LocalTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleCreateDTO {

        @NotNull(message = "ID bác sĩ không được để trống")
        @Schema(description = "ID bác sĩ")
        private String doctorId;

        @NotNull(message = "ID phòng khám không được để trống")
        @Schema(description = "ID phòng khám")
        private String clinicId;

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

        @Schema(description = "Trạng thái lịch khám", example = "PENDING")
        private ScheduleStatus status = ScheduleStatus.PENDING;
}
