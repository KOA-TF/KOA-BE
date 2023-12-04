package com.koa.coremodule.member.domain.repository;

import com.koa.coremodule.member.domain.entity.MemberDetail;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, Long> {
    @Query("select m from MemberDetail m where m.member.id = :memberId")
    Optional<MemberDetail> findByMemberId(Long memberId);

    @Query("select m from MemberDetail m join fetch m.member where m.member.id in :memberIdList")
    List<MemberDetail> findAllByMemberIdIn(List<Long> memberIdList);

    boolean existsByMemberId(Long memberId);

    void deleteByMemberId(Long memberId);
}
