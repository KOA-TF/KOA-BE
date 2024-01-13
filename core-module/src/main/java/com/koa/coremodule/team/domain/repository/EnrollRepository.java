package com.koa.coremodule.team.domain.repository;

import com.koa.coremodule.team.domain.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollRepository extends JpaRepository<Enroll, Long> {

    void deleteByTeamIdAndMemberId(Long teamId, Long memberId);
    void deleteAllByTeamId(Long teamId);

}
