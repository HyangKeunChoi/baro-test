package com.example.barotest.infrastructure.member;

import com.example.barotest.domain.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String password;
    private String name;

    public MemberEntity from(Member member) {
        return new MemberEntity(
            member.getId(),
            member.getUserId(),
            member.getPassword(),
            member.getName()
        );
    }

    public Member toModel() {
        return new Member(
                this.id,
                this.password,
                this.name
        );
    }
}
