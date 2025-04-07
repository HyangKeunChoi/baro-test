package com.example.barotest.infrastructure.delivery.repository;

import com.example.barotest.domain.delivery.Delivery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class DeliveryRepositoryImpl implements IDeliveryRepository {
    private final DeliveryJpaRepository deliveryJpaRepository;

    @Override
    public Delivery findById(int id) {
        return null;
    }
}
