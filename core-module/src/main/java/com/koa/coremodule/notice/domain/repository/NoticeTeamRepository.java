package com.koa.coremodule.notice.domain.repository;

import com.koa.coremodule.notice.domain.entity.NoticeTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeTeamRepository extends JpaRepository<NoticeTeam, Long> {
}
