package com.koa.coremodule.report.domain.repository;

import com.koa.coremodule.report.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Boolean existsByMemberIdAndCommentId(Long memberId, Long commentId);

    Optional<Report> findById(Long reportId);
}
