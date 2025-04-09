package com.example.barotest.feature.delivery.service;

import com.example.barotest.domain.delivery.Address;
import com.example.barotest.domain.delivery.Delivery;
import com.example.barotest.feature.delivery.controller.dto.DeliveryUpdateRequest;
import com.example.barotest.infrastructure.delivery.repository.IDeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final IDeliveryRepository deliveryRepository;

    public List<Delivery> getDeliveries(
        String userId,
        LocalDate searchStartDate,
        LocalDate searchEndDate
    ) {
        // TODO : goe  lt로 비교 연산하도록 수정 필요
        return deliveryRepository.findByUserIdAndCreatedAtBetween(
            userId,
            searchStartDate,
            searchEndDate
        );
    }

    @Transactional
    public void updateAddress(
        Long id,
        DeliveryUpdateRequest deliveryUpdateRequest
    ) {
        Delivery delivery = deliveryRepository.findById(id);
        Address newAddress = Address.builder()
            .street(deliveryUpdateRequest.getStreet())
            .city(deliveryUpdateRequest.getCity())
            .build();
        delivery.updateAddress(newAddress);
        deliveryRepository.save(delivery);
    }
}
