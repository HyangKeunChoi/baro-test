package com.example.barotest.infrastructure.delivery;

import com.example.barotest.domain.delivery.Delivery;
import com.example.barotest.domain.delivery.Status;
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

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private EAddress address;

    public DeliveryEntity from(Delivery delivery) {
        return new DeliveryEntity(
            delivery.getDeliveryId(),
            delivery.getDeliveryName(),
            delivery.getUserId(),
            delivery.getStatus(),
            new EAddress(
                delivery.getDeliveryAddress().getStreet(),
                delivery.getDeliveryAddress().getCity()
            )
        );
    }

    public Delivery toModel() {
        return Delivery.builder()
            .deliveryId(this.deliveryId)
            .deliveryName(this.deliveryName)
            .userId(this.userId)
            .status(this.status)
            .deliveryAddress(this.address.toModel())
            .build();
    }
}
