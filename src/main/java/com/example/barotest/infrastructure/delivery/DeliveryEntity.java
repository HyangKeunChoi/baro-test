package com.example.barotest.infrastructure.delivery;

import com.example.barotest.domain.delivery.Address;
import com.example.barotest.infrastructure.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "delivery")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    private String deliveryName;

    private Long userId;

    @Embedded
    private Address deliveryAddress;
}
