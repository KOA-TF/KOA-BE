package com.koa.coremodule.report.repository;

import com.koa.coremodule.notice.domain.entity.NoticeReport;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<NoticeReport, Long> {
}
