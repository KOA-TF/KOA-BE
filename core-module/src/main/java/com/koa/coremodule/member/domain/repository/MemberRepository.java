package com.koa.coremodule.member.domain.repository;

import com.koa.coremodule.member.domain.entity.Authority;
import com.koa.coremodule.member.domain.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByAuthorityAndEmailAndPassword(Authority authority, String email, String password);
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email);
}
