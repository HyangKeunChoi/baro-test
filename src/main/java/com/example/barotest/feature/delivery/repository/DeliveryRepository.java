package com.example.barotest.feature.delivery.repository;

import com.example.barotest.feature.delivery.domain.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
    // Define custom query methods if needed
}
