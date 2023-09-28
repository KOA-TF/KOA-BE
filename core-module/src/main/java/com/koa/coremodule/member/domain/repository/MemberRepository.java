package com.koa.coremodule.member.domain.repository;

import com.koa.coremodule.member.domain.entity.Authority;
import com.koa.coremodule.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmailAndAuthority(String email, Authority authority);

    Optional<Member> findByEmail(String email);
}
