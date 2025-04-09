package com.example.barotest.domain.delivery;

import com.example.barotest.common.exception.DeliveryStatusException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Delivery {
    Long deliveryId;
    String deliveryName;
    String userId;
    Status status;
    Address deliveryAddress;
    LocalDate createdAt;
    LocalDate updatedAt;

    @Builder
    public Delivery(
        Long deliveryId,
        String deliveryName,
        String userId,
        Status status,
        Address deliveryAddress,
        LocalDate createdAt,
        LocalDate updatedAt
    ) {
        this.deliveryId = deliveryId;
        this.deliveryName = deliveryName;
        this.userId = userId;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    Boolean isPending() {
        return this.status == Status.PENDING;
    }

    public Delivery updateAddress(
        Address newAddress
    ) {
        if (isPending()) throw new DeliveryStatusException();
        this.deliveryAddress = newAddress;
        return this;
    }
}
