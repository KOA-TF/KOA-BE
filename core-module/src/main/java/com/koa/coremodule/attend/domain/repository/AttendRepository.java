package com.koa.coremodule.attend.domain.repository;

import com.koa.coremodule.attend.domain.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttendRepository extends JpaRepository<Attend, Long>, AttendDynamicRepository {

    Attend findAttendByMemberIdAndCurriculumId(Long memberId, Long curriculumId);

    @Query("SELECT COUNT(a) > 0 FROM Attend a WHERE a.curriculum.id = :curriculumId AND a.member.id = :memberId")
    boolean existsByCurriculumIdAndMemberId(@Param("curriculumId") Long curriculumId, @Param("memberId") Long memberId);

}
