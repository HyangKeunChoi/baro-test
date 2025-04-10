package com.example.barotest.common;

import com.example.barotest.feature.delivery.service.DeliveryService;
import com.example.barotest.feature.member.service.MemberService;
import com.example.barotest.infrastructure.member.repository.MemberJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseApplicationContext {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @MockBean
    protected MemberService memberService;

    @Autowired
    protected MemberJpaRepository memberRepository;

    @MockBean
    protected DeliveryService deliveryService;

    @Value("${secret.key}")
    protected String secretKey;
}
