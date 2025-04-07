package com.example.barotest.infrastructure.member.repository;

import com.example.barotest.infrastructure.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
