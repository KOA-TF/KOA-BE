package com.koa.coremodule.team.domain.repository;

import com.koa.coremodule.team.domain.entity.Enroll;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnrollRepository extends JpaRepository<Enroll, Long> {

    void deleteByTeamIdAndMemberId(Long teamId, Long memberId);
    void deleteAllByTeamId(Long teamId);

    @Query("""
              select e from Enroll e
              join fetch Team t on e.team.id = t.id
              join fetch Curriculum c on t.curriculum.id = c.id
              where e.member.id = :memberId
              """)
    List<Enroll> findAllByMemberId(@Param("memberId") Long memberId);

}
