package com.koa.apimodule.command.api.report.service;

import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.report.domain.entity.Report;
import com.koa.coremodule.report.application.dto.ReportRequest;
import com.koa.coremodule.report.application.exception.ReportException;
import com.koa.coremodule.report.application.service.ReportQueryService;
import com.koa.coremodule.report.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportUseCase {

    private final ReportQueryService reportQueryService;
    private final ReportMapper reportMapper;

    public Long createReport(ReportRequest request) {

        Optional<Report> existingReport = reportQueryService.findById(request.getCommentId());

        if (existingReport.isPresent()) {
            throw new ReportException(Error.DUPLICATE_REPORT);
        } else {
            Report reportEntity = reportMapper.toReportEntity(request);
            Report savedReport = reportQueryService.save(reportEntity);
            return savedReport.getId();
        }
    }

}
