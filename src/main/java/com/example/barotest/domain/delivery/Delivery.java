package com.example.barotest.domain.delivery;

import java.time.LocalDate;

public class Delivery {
    Long deliveryId;
    String deliveryName;
    Long userId;
    Address deliveryAddress;
    LocalDate createdAt;
    LocalDate updatedAt;
}
