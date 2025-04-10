package com.example.barotest.domain.delivery;

import com.example.barotest.common.exception.DeliveryStatusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Delivery 도메인 테스트")
class DeliveryTest {

    @Test
    @DisplayName("Delivery 객체 생성 테스트")
    void createDelivery() {
        // Given
        Long deliveryId = 1L;
        String deliveryName = "테스트 배송";
        String userId = "testUser";
        Status status = Status.PENDING;
        Address address = Address.builder()
            .city("서울")
            .street("강남대로")
            .build();
        LocalDate createdAt = LocalDate.now();
        LocalDate updatedAt = LocalDate.now();

        // When
        Delivery delivery = Delivery.builder()
            .deliveryId(deliveryId)
            .deliveryName(deliveryName)
            .userId(userId)
            .status(status)
            .deliveryAddress(address)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();

        // Then
        assertNotNull(delivery);
        assertEquals(deliveryId, delivery.getDeliveryId());
        assertEquals(deliveryName, delivery.getDeliveryName());
        assertEquals(userId, delivery.getUserId());
        assertEquals(status, delivery.getStatus());
        assertEquals(address, delivery.getDeliveryAddress());
        assertEquals(createdAt, delivery.getCreatedAt());
        assertEquals(updatedAt, delivery.getUpdatedAt());
    }

    @Nested
    @DisplayName("배송 상태 확인 테스트")
    class IsPendingTest {
        @ParameterizedTest
        @EnumSource(value = Status.class, names = {"PENDING"})
        @DisplayName("상태가 PENDING이면 true를 반환한다")
        void isPendingReturnsTrue(Status pendingStatus) {
            // Given
            Delivery delivery = Delivery.builder()
                .status(pendingStatus)
                .build();

            // When
            boolean isPending = delivery.isPending();

            // Then
            assertTrue(isPending);
        }

        @ParameterizedTest
        @EnumSource(value = Status.class, names = {"CANCELLED", "DELIVERING", "DELIVERED"})
        @DisplayName("상태가 PENDING이 아니면 false를 반환한다")
        void isPendingReturnsFalse(Status notPendingStatus) {
            // Given
            Delivery delivery = Delivery.builder()
                .status(notPendingStatus)
                .build();

            // When
            boolean isPending = delivery.isPending();

            // Then
            assertFalse(isPending);
        }
    }

    @Nested
    @DisplayName("배송 주소 업데이트 테스트")
    class UpdateAddressTest {

        @Test
        @DisplayName("배송 상태가 PENDING이면 주소를 업데이트하고 업데이트된 Delivery 객체를 반환한다")
        void updateAddressUpdatesAddressWhenPending() {
            // Given
            Address oldAddress = Address.builder().city("서울").street("기존 주소").build();
            Delivery delivery = Delivery.builder()
                .status(Status.PENDING)
                .deliveryAddress(oldAddress)
                .build();
            Address newAddress = Address.builder().city("부산").street("새로운 주소").build();

            // When
            Delivery updatedDelivery = delivery.updateAddress(newAddress);

            // Then
            assertEquals(newAddress, updatedDelivery.getDeliveryAddress());
            assertEquals(delivery.getDeliveryId(), updatedDelivery.getDeliveryId());
            assertEquals(delivery.getDeliveryName(), updatedDelivery.getDeliveryName());
            assertEquals(delivery.getUserId(), updatedDelivery.getUserId());
            assertEquals(delivery.getStatus(), updatedDelivery.getStatus());
            assertEquals(delivery.getCreatedAt(), updatedDelivery.getCreatedAt());
            assertEquals(delivery.getUpdatedAt(), updatedDelivery.getUpdatedAt());
        }

        @ParameterizedTest
        @EnumSource(value = Status.class, names = {"CANCELLED", "DELIVERING", "DELIVERED"})
        @DisplayName("배송 상태가 PENDING이 아니면 DeliveryStatusException이 발생한다")
        void updateAddressThrowsExceptionWhenNotPending(Status notPendingStatus) {
            // Given
            Delivery delivery = Delivery.builder()
                .status(notPendingStatus)
                .deliveryAddress(Address.builder().city("서울").street("기존 주소").build())
                .build();
            Address newAddress = Address.builder().city("부산").street("새로운 주소").build();

            // When & Then
            assertThrows(DeliveryStatusException.class, () -> delivery.updateAddress(newAddress));
        }
    }
}
