package com.example.barotest.feature.delivery.service;

import com.example.barotest.infrastructure.delivery.repository.IDeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DeliveryService{

    private final IDeliveryRepository deliveryRepository;

    public void getDelivery(int id) {

    }
}
