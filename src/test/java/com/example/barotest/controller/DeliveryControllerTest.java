package com.example.barotest.controller;

import com.example.barotest.common.BaseApplicationContext;
import com.example.barotest.domain.delivery.Address;
import com.example.barotest.domain.delivery.Delivery;
import com.example.barotest.domain.delivery.Status;
import com.example.barotest.domain.member.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class DeliveryControllerTest extends BaseApplicationContext {

    @DisplayName("배달 조회 API 테스트")
    @Test
    public void getDeliveryTest() throws Exception {
        // given
        String userId = "1";
        Member member = Member.builder()
            .userId(userId)
            .password("asdfasdf123123D")
            .name("최향근")
            .build();
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now();

        // 더미 데이터
        Delivery delivery1 = Delivery
            .builder()
            .deliveryId(1L)
            .deliveryName("Delivery1")
            .userId(userId)
            .status(Status.PENDING)
            .deliveryAddress(new Address("gangnam", "seoul"))
            .createdAt(LocalDate.now().minusDays(2))
            .updatedAt(LocalDate.now())
            .build();

        Delivery delivery2 = Delivery
            .builder()
            .deliveryId(2L)
            .deliveryName("Delivery2")
            .userId(userId)
            .status(Status.PENDING)
            .createdAt(LocalDate.now().minusDays(1))
            .updatedAt(LocalDate.now())
            .deliveryAddress(new Address("gangnam", "seoul"))
            .build();

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        Claims claims = Jwts.claims();
        claims.put("userId", member.getUserId());
        Date expiredAt = Date.from(Instant.now().plus(Duration.ofDays(1L)));

        String jwt = Jwts.builder()
            .signWith(key)
            .addClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(expiredAt)
            .compact();

        List<Delivery> deliveryList = List.of(delivery1, delivery2);

        // when
        when(deliveryService.getDeliveries("1", startDate, endDate)).thenReturn(deliveryList);

        // then
        mockMvc.perform(get("/api/v1/delivery")
                .header("x-barogo-access-token", jwt)
                .param("searchStartDate", startDate.toString())
                .param("searchEndDate", endDate.toString())
                .header("userId", userId)  // userId 헤더로 전달
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())  // 상태 코드 200 OK
            .andExpect(jsonPath("$.length()").value(2))  // 배달 리스트 개수 확인
            .andExpect(jsonPath("$[0].deliveryName").value("Delivery1"))  // 첫 번째 배달의 주문 ID 확인
            .andExpect(jsonPath("$[1].deliveryName").value("Delivery2"));  // 두 번째 배달의 주문 ID 확인
    }
}
