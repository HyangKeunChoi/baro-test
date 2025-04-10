package com.example.barotest.feature.delivery.controller.dto;

import com.example.barotest.common.validator.SearchDateRangeValid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@SearchDateRangeValid(startDate = "searchStartDate", endDate = "searchEndDate")
public class DeliverySearchRequest {

    @NotNull(message = "시작일은 null 일 수 없습니다.")
    private LocalDate searchStartDate;

    @NotNull(message = "종료일은 null 일 수 없습니다.")
    private LocalDate searchEndDate;
}
