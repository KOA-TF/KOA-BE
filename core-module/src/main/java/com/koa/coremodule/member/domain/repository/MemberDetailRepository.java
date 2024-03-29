package com.koa.coremodule.member.domain.repository;

import com.koa.coremodule.member.domain.entity.MemberDetail;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, Long>, MemberDetailDynamicRepository {
    @Query("select m from MemberDetail m where m.member.id = :memberId")
    Optional<MemberDetail> findByMemberId(@Param("memberId") Long memberId);

    @Query("select m from MemberDetail m join fetch m.member where m.member.id in :memberIdList")
    List<MemberDetail> findAllByMemberIdIn(@Param("memberIdList") List<Long> memberIdList);

    boolean existsByMemberId(Long memberId);

    void deleteByMemberId(Long memberId);
}
