package com.example.barotest.feature.member.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MemberSigninRequest(
    @NotNull(message = "아이디를 입력해 주세요.")
    String userId,

    @NotBlank(message = "패스워드를 입력해 주세요.")
    String password
) {}
