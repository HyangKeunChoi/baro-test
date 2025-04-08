package com.example.barotest.infrastructure.member.repository;

import com.example.barotest.domain.member.Member;
import com.example.barotest.infrastructure.member.MemberEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class MemberRepositoryImpl implements IMemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(MemberEntity.from(member)).toModel();
    }

    @Override
    public Boolean existsByUserId(Long id) {
        return memberJpaRepository.existsByUserId(id);
    }

    @Override
    public Optional<Member> findByUserIdAndPassword(Long id, String password) {
        return Optional.ofNullable(memberJpaRepository.findByUserIdAndPassword(id, password).toModel());
    }
}
