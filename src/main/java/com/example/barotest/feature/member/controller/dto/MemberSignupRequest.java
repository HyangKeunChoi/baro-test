package com.example.barotest.feature.member.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberSignupRequest(
    @NotBlank(message = "아이디를 입력해 주세요.") String userId,

    @NotBlank(message = "패스워드를 입력해 주세요.")
    @Size(min = 12, message = "패스워드는 12자리 이상이어야 합니다.")
    String password,

    @NotBlank(message = "이름을 입력해 주세요.") String name
) {}
