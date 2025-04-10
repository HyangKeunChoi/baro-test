package com.example.barotest.feature.delivery.controller;

import com.example.barotest.common.argument.User;
import com.example.barotest.domain.delivery.Delivery;
import com.example.barotest.feature.delivery.controller.dto.DeliverySearchRequest;
import com.example.barotest.feature.delivery.controller.dto.DeliveryUpdateRequest;
import com.example.barotest.feature.delivery.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

//     배달 조회
//    1.	배달 조회 시, 기간은 필수로 받아주세요.
//    (또한, 조회 가능한 기간은 최대 3일 입니다. 그 외의 조건도 조회시에 필요하다고 생각되시면 추가해주세요.)
//    2.	기간 내에 사용자가 주문한 배달의 리스트를 제공합니다.

    @GetMapping
    public ResponseEntity<List<Delivery>> getDelivery(
        @User String userId,
        @Valid @ModelAttribute DeliverySearchRequest searchRequest
    ) {
        return ResponseEntity.ok().body(
            deliveryService.getDeliveries(userId, searchRequest.getSearchStartDate(), searchRequest.getSearchEndDate())
        );
    }

    //    배달 주문 수정 (도착지 주소 변경)
//    1.	사용자로부터 도착지 주소를 요청 받아 처리합니다.
//    2.	사용자가 변경 가능한 배달인 경우에만 수정이 가능합니다.
    @PatchMapping("/{id}")
    public ResponseEntity updateAddress(
        @PathVariable Long id,
        @RequestBody DeliveryUpdateRequest deliveryUpdateRequest
    ) {
        deliveryService.updateAddress(id, deliveryUpdateRequest);
        return ResponseEntity.ok().build();
    }
}
