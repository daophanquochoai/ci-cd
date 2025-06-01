package com.ptithcm.clinic_booking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
@Data
public class PageResponse<T> {
    @Schema(description = "Danh sách dữ liệu của trang hiện tại")
    private List<T> data;

    @Schema(description = "Chỉ số trang hiện tại (bắt đầu từ 0)", example = "0")
    private int currentPage;

    @Schema(description = "Tổng số trang", example = "5")
    private int totalPages;

    @Schema(description = "Tổng số phần tử", example = "42")
    private long totalItems;

    public PageResponse(List<T> data, int currentPage, int totalPages, long totalItems) {
        this.data = data;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public PageResponse(Page<T> page) {
        this.data = page.getContent();
        this.currentPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalItems = page.getTotalElements();
    }

    public PageResponse(List<T> data, Page<?> page) {
        this.data = data;
        this.currentPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalItems = page.getTotalElements();
    }
}
