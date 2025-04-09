package com.example.barotest.infrastructure.member.repository;

import com.example.barotest.infrastructure.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {
    Boolean existsByUserId(String userId);

    Optional<MemberEntity> findByUserId(String userId);
}
