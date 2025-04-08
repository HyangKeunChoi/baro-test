package com.example.barotest.feature.member.service;

import com.example.barotest.common.exception.DuplicateUserIdException;
import com.example.barotest.common.exception.UserNotFoundException;
import com.example.barotest.config.PasswordConfig;
import com.example.barotest.domain.member.Member;
import com.example.barotest.feature.member.controller.dto.MemberSigninRequest;
import com.example.barotest.feature.member.controller.dto.MemberSignupRequest;
import com.example.barotest.infrastructure.member.repository.IMemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static com.example.barotest.common.response.ErrorInfo.DUPLICATE_USER_ID_ERROR;
import static com.example.barotest.common.response.ErrorInfo.USER_NOT_FOUND_ERROR;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberService {

    @Value("${secret.key}")
    private String secretKey;

    private final IMemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signup(MemberSignupRequest memberRequest) {
        Member member = Member.builder()
                .userId(memberRequest.userId())
                .password(memberRequest.password())
                .name(memberRequest.name())
                .build();

        validationMember(member);
        member.validate();

        Member signUpMember = Member.builder()
            .userId(member.getUserId())
            .password(encodePassword(memberRequest.password()))
            .name(memberRequest.name())
            .build();

        return memberRepository.save(signUpMember).getUserId();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    void validationMember(Member member) {
        // 이미 등록되어 있는 아이디 입니다.
        Boolean isExist = memberRepository.existsByUserId(member.getUserId());
        if (isExist) throw new DuplicateUserIdException();
    }

    public String signin(MemberSigninRequest memberRequest) {
        Member signinMember = Member.builder()
                .userId(memberRequest.id())
                .password(encodePassword(memberRequest.password()))
                .build();
        Member member = memberRepository.findByUserIdAndPassword(signinMember.getUserId(), signinMember.getPassword())
                .orElseThrow(UserNotFoundException::new);

        return generateJwtToken(member.getUserId());
    }

    private String generateJwtToken(Long userId) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        Date expiredAt = Date.from(Instant.now().plus(Duration.ofDays(1L)));
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        return Jwts.builder()
                .signWith(key)
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiredAt)
                .compact();
    }
}
