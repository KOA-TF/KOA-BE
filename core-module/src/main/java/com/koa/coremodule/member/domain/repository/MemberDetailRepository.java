package com.koa.coremodule.member.domain.repository;

import com.koa.coremodule.member.domain.entity.MemberDetail;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, Long> {
    @Query("select m from MemberDetail m where m.member.id = :memberId")
    Optional<MemberDetail> findByMemberId(Long memberId);

    boolean existsByMemberId(Long memberId);

    void deleteByMemberId(Long memberId);
}
