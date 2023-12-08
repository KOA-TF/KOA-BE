package com.koa.coremodule.attend.domain.repository;

import com.koa.coremodule.attend.domain.entity.Attend;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendRepository extends JpaRepository<Attend, Long>, AttendDynamicRepository {

    @Query("SELECT a.status, COUNT(a) FROM Attend a WHERE a.member.id = :memberId GROUP BY a.status")
    List<Object[]> findAttendStatusCountByMemberId(@Param("memberId") Long memberId);

}
