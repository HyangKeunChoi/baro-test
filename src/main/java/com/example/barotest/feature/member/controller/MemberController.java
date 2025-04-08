package com.example.barotest.feature.member.controller;

import com.example.barotest.feature.member.controller.dto.MemberSignupRequest;
import com.example.barotest.feature.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public void signup(
        @Validated @RequestBody MemberSignupRequest memberRequest
    ) {
        memberService.signup(memberRequest);
    }

    // 로그인
    //    1.	사용자로부터 ID, 비밀번호를 입력받아 로그인을 처리합니다.
    //    2.	ID와 비밀번호가 이미 가입되어 있는 회원의 정보와 일치하면 로그인이 되었다는
    //    응답으로 AccessToken 을 제공합니다.

}
