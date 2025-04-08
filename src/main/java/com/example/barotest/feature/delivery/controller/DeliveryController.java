package com.example.barotest.feature.delivery.controller;

import com.example.barotest.common.argument.User;
import com.example.barotest.common.validator.SearchDateRangeValid;
import com.example.barotest.feature.delivery.service.DeliveryService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Validated
@RestController("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController{

    private final DeliveryService deliveryService;

//     배달 조회
//    1.	배달 조회 시, 기간은 필수로 받아주세요.
//    (또한, 조회 가능한 기간은 최대 3일 입니다. 그 외의 조건도 조회시에 필요하다고 생각되시면 추가해주세요.)
//    2.	기간 내에 사용자가 주문한 배달의 리스트를 제공합니다.

    @GetMapping
    public void getDelivery(
        @User Long userId,

        @RequestParam
        @NotBlank(message = "시작일은 비어있을 수 없습니다.")
        LocalDate searchStartDate,

        @RequestParam
        @NotBlank(message = "종료일은 비어 있을수 없습니다.")
        @SearchDateRangeValid
        LocalDate searchEndDate
    ) {
        deliveryService.getDelivery(userId, searchStartDate, searchEndDate);
    }

    //    배달 주문 수정 (도착지 주소 변경)
//    1.	사용자로부터 도착지 주소를 요청 받아 처리합니다.
//    2.	사용자가 변경 가능한 배달인 경우에만 수정이 가능합니다.
    @PatchMapping("/{id}")
    public void updateAddress(@PathVariable int id) {

    }
}
