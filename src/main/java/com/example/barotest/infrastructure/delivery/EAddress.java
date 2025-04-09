package com.example.barotest.infrastructure.delivery;

import com.example.barotest.domain.delivery.Address;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class EAddress {
    private String street;
    private String city;

    @Builder
    public EAddress(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public Address toModel() {
        return Address.builder()
                .street(this.street)
                .city(this.city)
                .build();
    }
}
