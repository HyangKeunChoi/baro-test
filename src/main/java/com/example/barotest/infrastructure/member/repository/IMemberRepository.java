package com.example.barotest.infrastructure.member.repository;

import com.example.barotest.domain.member.Member;

import java.util.Optional;

public interface IMemberRepository {
    Member save(Member member);
    Boolean existsByUserId(Long id);
    Optional<Member> findByUserIdAndPassword(Long id, String password);
}
