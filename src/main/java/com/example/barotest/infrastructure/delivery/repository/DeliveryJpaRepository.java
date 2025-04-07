package com.example.barotest.infrastructure.delivery.repository;

import com.example.barotest.infrastructure.delivery.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryJpaRepository extends JpaRepository<DeliveryEntity, Long> {
    // Define custom query methods if needed
}
