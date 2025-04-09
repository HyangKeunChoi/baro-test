package com.example.barotest.infrastructure.member.repository;

import com.example.barotest.common.exception.BaseException;
import com.example.barotest.common.exception.UserNotFoundException;
import com.example.barotest.common.response.ErrorInfo;
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
    public Member findByUserIdAndPassword(Long id, String password) {
        MemberEntity member = memberJpaRepository.findByUserIdAndPassword(id, password)
            .orElseThrow(() -> new UserNotFoundException());
        return member.toModel();
    }
}
