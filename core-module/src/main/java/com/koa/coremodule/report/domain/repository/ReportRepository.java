package com.koa.coremodule.report.domain.repository;

import com.koa.coremodule.report.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Report findByCommentIdAndMemberId(Long memberId, Long commentId);
}
