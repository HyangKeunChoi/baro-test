package com.example.barotest.feature.delivery.controller.dto;

import lombok.Getter;

@Getter
public class DeliveryUpdateRequest {
    private String street;
    private String city;

    public DeliveryUpdateRequest(String street, String city) {
        this.street = street;
        this.city = city;
    }
}
