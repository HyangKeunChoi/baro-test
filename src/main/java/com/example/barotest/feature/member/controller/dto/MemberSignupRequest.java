package com.example.barotest.feature.member.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberSignupRequest(
    @NotBlank(message = "아이디를 입력해 주세요.") String id,
    @NotBlank(message = "패스워드를 입력해 주세요.") String password,
    @NotBlank(message = "이름을 입력해 주세요.") String name
) {}