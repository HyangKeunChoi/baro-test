package com.example.barotest.domain.member;

import com.example.barotest.common.exception.PasswordLengthException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {
    Long userId;
    String password;
    String name;

    @Builder
    public Member(Long userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    // 회원가입
    // 1.	회원가입시 필요한 정보는 ID, 비밀번호, 사용자 이름 입니다.
    // 2.	비밀번호는 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상으로
    // 3.	12자리 이상의 문자열로 생성해야 합니다.
    public void validate() {
        if (password == null || password.length() < 12) {
            throw new PasswordLengthException();
        }
        int kindCount = 0;
        if (password.matches(".*[A-Z].*")) kindCount++; // 대문자 포함 여부
        if (password.matches(".*[a-z].*")) kindCount++; // 소문자 포함 여부
        if (password.matches(".*\\d.*")) kindCount++;  // 숫자 포함 여부
        if (password.matches(".*[^a-zA-Z0-9].*")) kindCount++; // 특수문자 포함 여부

        if (kindCount < 3) {
            throw new IllegalArgumentException("비밀번호는 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상을 포함해야 합니다.");
        }
    }
}
