package com.example.barotest.infrastructure.delivery.repository;

import com.example.barotest.infrastructure.delivery.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DeliveryJpaRepository extends JpaRepository<DeliveryEntity, Long> {
    Optional<DeliveryEntity> findByDeliveryId(Long id);
    Optional<List<DeliveryEntity>> findByUserIdAndCreatedAtBetween(
        String userId,
        LocalDateTime searchStartDate,
        LocalDateTime searchEndDate
    );
}
