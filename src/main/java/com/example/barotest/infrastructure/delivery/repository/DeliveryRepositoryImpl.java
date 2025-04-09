package com.example.barotest.infrastructure.delivery.repository;

import com.example.barotest.domain.delivery.Delivery;
import com.example.barotest.infrastructure.delivery.DeliveryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class DeliveryRepositoryImpl implements IDeliveryRepository {
    private final DeliveryJpaRepository deliveryJpaRepository;

    @Override
    public Delivery findById(Long id) {
        DeliveryEntity deliveryEntity = deliveryJpaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Delivery not found"));

        return deliveryEntity.toModel();
    }

    @Override
    public List<Delivery> findByUserIdAndCreatedAtBetween(Long userId, LocalDate searchStartDate, LocalDate searchEndDate) {
        List<DeliveryEntity> deliveryEntities = deliveryJpaRepository.findByUserIdAndCreatedAtBetween(
                userId, searchStartDate, searchEndDate)
            .orElse(Collections.emptyList());

        return deliveryEntities.stream()
            .map(DeliveryEntity::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public Delivery save(Delivery delivery) {
        DeliveryEntity deliveryEntity = new DeliveryEntity().from(delivery);
        return deliveryJpaRepository.save(deliveryEntity).toModel();
    }
}
