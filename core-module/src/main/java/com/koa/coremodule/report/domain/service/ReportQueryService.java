package com.koa.coremodule.report.domain.service;

import com.koa.coremodule.report.domain.entity.Report;
import com.koa.coremodule.report.domain.exception.DuplicateReportException;
import com.koa.coremodule.report.domain.exception.ReportError;
import com.koa.coremodule.report.domain.exception.ReportNotFoundException;
import com.koa.coremodule.report.domain.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportQueryService {

    private final ReportRepository reportRepository;

    public List<Report> findAll() {

        return reportRepository.findAll();
    }

    public void checkReportExists(Long memberId, Long commentId) {
        if(reportRepository.existsByMemberIdAndCommentId(memberId, commentId)) {
            throw new DuplicateReportException(ReportError.DUPLICATE_REPORT);
        }
    }

    public Report save(Report report) {

        return reportRepository.save(report);
    }

    public void hideReport(Long reportId) {

        Report report = reportRepository.findById(reportId).orElseThrow(() -> new ReportNotFoundException(ReportError.REPORT_NOT_FOUND));
        report.hide();
    }
}
