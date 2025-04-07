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

    // 회원가입
    // 1.	회원가입시 필요한 정보는 ID, 비밀번호, 사용자 이름 입니다.
    // 2.	비밀번호는 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상으로
    //		12자리 이상의 문자열로 생성해야 합니다.
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
