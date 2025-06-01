package com.ptithcm.clinic_booking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRequest {
    @Schema(description = "Số trang (bắt đầu từ 0)", example = "0", minimum = "0")
    @Min(value = 0, message = "Page không được nhỏ hơn 0")
    private int page = 0;

    @Schema(description = "Số lượng bản ghi mỗi trang", example = "10", minimum = "1", maximum = "100")
    @Min(value = 1, message = "Size phải lớn hơn hoặc bằng 1")
    @Max(value = 100, message = "Size không được vượt quá 100")
    private int size = 10;

    @Schema(description = "Thuộc tính dùng để sắp xếp", example = "id")
    @NotBlank(message = "SortBy không được để trống")
    private String sortBy = "id";

    @Schema(description = "Hướng sắp xếp", example = "asc", allowableValues = {"asc", "desc"})
    private String direction = "asc";

    public Pageable toPageable() {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return PageRequest.of(this.page, this.size, sort);
    }

    public PaginationRequest(int page, int size, String sortBy) {
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
    }

}
