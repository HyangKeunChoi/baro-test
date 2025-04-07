package com.example.barotest.feature.member.repository;

import com.example.barotest.feature.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
