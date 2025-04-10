package com.example.barotest.infrastructure.member.repository;

import com.example.barotest.domain.member.Member;

public interface IMemberRepository {
    Member save(Member member);

    Boolean existsByUserId(String userId);

    Member findByUserId(String userId);
}
