package com.koa.coremodule.report.repository;

import com.koa.coremodule.notice.domain.entity.NoticeReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<NoticeReport, Long> {
}
