package com.example.barotest.infrastructure.delivery.repository;

import com.example.barotest.infrastructure.delivery.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DeliveryJpaRepository extends JpaRepository<DeliveryEntity, Long> {
    Optional<List<DeliveryEntity>> findByUserIdAndCreatedAtBetween(
        Long userId,
        LocalDate searchStartDate,
        LocalDate searchEndDate
    );
}
