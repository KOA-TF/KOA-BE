package com.koa.coremodule.notice.domain.repository;

import com.koa.coremodule.notice.domain.entity.NoticeView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeViewRepository extends JpaRepository<NoticeView, Long> {
}
