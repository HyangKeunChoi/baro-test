package com.example.barotest.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Member 도메인 테스트")
class MemberTest {

    @Test
    @DisplayName("Member 객체 생성 테스트")
    void createMember() {
        // Given
        String userId = "testUser";
        String password = "Test123!";
        String name = "테스트 사용자";

        // When
        Member member = Member.builder()
            .userId(userId)
            .password(password)
            .name(name)
            .build();

        // Then
        assertNotNull(member);
        assertEquals(userId, member.getUserId());
        assertEquals(password, member.getPassword());
        assertEquals(name, member.getName());
    }

    @Nested
    @DisplayName("비밀번호 유효성 검증 테스트")
    class ValidatePasswordTest {

        @ParameterizedTest
        @MethodSource("invalidPasswordProvider")
        @DisplayName("유효하지 않은 비밀번호는 IllegalArgumentException이 발생한다")
        void invalidPasswordThrowsIllegalArgumentException(String password, String expectedMessage) {
            Member member = Member.builder()
                .userId("test")
                .password(password)
                .name("test")
                .build();

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, member::validatePassword);
            assertEquals(expectedMessage, exception.getMessage());
        }

        static Stream<Arguments> invalidPasswordProvider() {
            return Stream.of(
                Arguments.of("lowercaseonly", "비밀번호는 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상을 포함해야 합니다."), // 종류 부족 (소문자만)
                Arguments.of("UPPERCASEONLY", "비밀번호는 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상을 포함해야 합니다."), // 종류 부족 (대문자만)
                Arguments.of("123456789012", "비밀번호는 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상을 포함해야 합니다."), // 종류 부족 (숫자만)
                Arguments.of("!@#$%^&*()_+", "비밀번호는 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상을 포함해야 합니다.") // 종류 부족 (특수문자만)
            );
        }
    }
}

