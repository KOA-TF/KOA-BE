package com.koa.coremodule.report.domain.service;

import com.koa.coremodule.report.domain.entity.Report;
import com.koa.coremodule.report.domain.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportQueryService {

    private final ReportRepository reportRepository;

    public Optional<Report> findByIds(Long memberId, Long commentId) {

        return Optional.ofNullable(reportRepository.findByCommentIdAndMemberId(memberId, commentId));
    }

    public Report save(Report report) {

        return reportRepository.save(report);
    }
}