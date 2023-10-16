package com.koa.coremodule.notice.repository;

import com.koa.coremodule.notice.domain.entity.Notice;
import org.springframework.data.repository.Repository;

public interface NoticeRepository extends Repository<Notice, Long>, NoticeDynamicRepository {
}
