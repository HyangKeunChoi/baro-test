package com.example.barotest.infrastructure.delivery.repository;

import com.example.barotest.domain.delivery.Delivery;

import java.time.LocalDate;
import java.util.List;

public interface IDeliveryRepository {
    Delivery findByDeliveryId(Long id);

    List<Delivery> findByUserIdAndCreatedAtBetween(
        String userId,
        LocalDate searchStartDate,
        LocalDate searchEndDate
    );

    Delivery save(Delivery delivery);
}
