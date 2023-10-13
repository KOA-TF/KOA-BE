package com.koa.coremodule.report.application.service;

import com.koa.coremodule.notice.domain.entity.NoticeReport;
import com.koa.coremodule.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportQueryService {

    private final ReportRepository reportRepository;

    public Optional<NoticeReport> findById(Long commentId) {

        return reportRepository.findById(commentId);
    }

    public NoticeReport save(NoticeReport report) {

        return reportRepository.save(report);
    }
}
