package com.koa.coremodule.notice.domain.repository;

import com.koa.coremodule.notice.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeDynamicRepository {
}
