package com.example.barotest.feature.member.service;

import com.example.barotest.config.PasswordConfig;
import com.example.barotest.feature.member.controller.dto.MemberSignupRequest;
import com.example.barotest.infrastructure.member.repository.IMemberRepository;
import com.example.barotest.infrastructure.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberService {
    private final IMemberRepository memberRepository;
    private final PasswordConfig passwordConfig;

    public void signup(MemberSignupRequest memberRequest) {

    }

    void validation() {
        // 이미 등록되어 있는 아이디 입니다.

        // 비밀번호는 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상으로

        // 12자리 이상의 문자열로 생성해야 합니다.
    }
}
