package com.example.barotest.infrastructure.delivery.repository;

import com.example.barotest.domain.delivery.Delivery;

public interface IDeliveryRepository {
    public Delivery findById(int id);
}
