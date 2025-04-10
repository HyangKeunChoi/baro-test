package com.example.barotest.controller;

import com.example.barotest.common.BaseApplicationContext;
import com.example.barotest.domain.member.Member;
import com.example.barotest.feature.member.controller.dto.MemberSigninRequest;
import com.example.barotest.feature.member.controller.dto.MemberSignupRequest;
import com.example.barotest.infrastructure.member.MemberEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class MemberControllerTest extends BaseApplicationContext {

    @BeforeEach
    void clean() {
        memberRepository.deleteAllInBatch();
    }

    @DisplayName("회원 가입 컨트롤러 테스트")
    @Test
    public void signUpTest() throws Exception {

        // given
        MemberSignupRequest request = new MemberSignupRequest("testUser", "password123#$%AB", "최향근");

        // when
        mockMvc.perform(post("/api/v1/member/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("로그인 컨트롤러 테스트")
    @Test
    public void signInTest() throws Exception {
        // given
        Member member = Member.builder()
            .userId("testUser")
            .password(passwordEncoder.encode("password123#$%AB"))
            .name("최향근")
            .build();
        memberRepository.save(new MemberEntity().from(member));

        MemberSigninRequest request = new MemberSigninRequest("testUser", "password123#$%AB");

        String expectedToken = "fakeAccessToken";

        when(memberService.signin(request)).thenReturn(expectedToken);

        // when
        mockMvc.perform(post("/api/v1/member/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(expectedToken));
    }
}
