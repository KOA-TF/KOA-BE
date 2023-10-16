package com.koa.coremodule.report.application.service;

import com.koa.coremodule.report.domain.entity.Report;
import com.koa.coremodule.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportQueryService {

    private final ReportRepository reportRepository;

    public Optional<Report> findById(Long commentId) {

        return reportRepository.findById(commentId);
    }

    public Report save(Report report) {

        return reportRepository.save(report);
    }
}
