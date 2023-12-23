package com.koa.coremodule.attend.domain.repository;

import com.koa.coremodule.attend.domain.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendRepository extends JpaRepository<Attend, Long>, AttendDynamicRepository {

    Attend findAttendByMemberIdAndCurriculumId(Long memberId, Long curriculumId);

}
