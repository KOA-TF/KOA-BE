package com.koa.coremodule.notice.domain.repository;

import com.koa.coremodule.notice.domain.entity.NoticeImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeImageRepository extends JpaRepository<NoticeImage, Long> {
}
