package com.example.barotest.infrastructure.delivery.repository;

import com.example.barotest.common.exception.DeliveryNotFoundException;
import com.example.barotest.domain.delivery.Delivery;
import com.example.barotest.infrastructure.delivery.DeliveryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class DeliveryRepositoryImpl implements IDeliveryRepository {
    private final DeliveryJpaRepository deliveryJpaRepository;

    @Override
    public Delivery findByDeliveryId(Long id) {
        DeliveryEntity deliveryEntity = deliveryJpaRepository.findByDeliveryId(id)
            .orElseThrow(() -> new DeliveryNotFoundException());

        return deliveryEntity.toModel();
    }

    @Override
    public List<Delivery> findByUserIdAndCreatedAtBetween(String userId, LocalDate searchStartDate, LocalDate searchEndDate) {
        LocalDateTime searchStartDateTime = searchStartDate.atStartOfDay();
        LocalDateTime searchEndDateTime = searchEndDate.atTime(23, 59, 59);

        List<DeliveryEntity> deliveryEntities = deliveryJpaRepository.findByUserIdAndCreatedAtBetween(
                userId, searchStartDateTime, searchEndDateTime)
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
