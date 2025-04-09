package com.example.barotest.domain.delivery;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Address {
    private String street;
    private String city;

    @Builder
    public Address(
        String street,
        String city
    ) {
        this.street = street;
        this.city = city;
    }
}
