package com.koa.coremodule.report.application.service;

import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.report.domain.entity.Report;
import com.koa.coremodule.report.application.dto.ReportRequest;
import com.koa.coremodule.report.domain.exception.ReportException;
import com.koa.coremodule.report.domain.service.ReportQueryService;
import com.koa.coremodule.report.application.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportSaveUseCase {

    private final ReportQueryService reportQueryService;
    private final ReportMapper reportMapper;

    public Long createReport(ReportRequest request) {

        Optional<Report> existingReport = reportQueryService.findByIds(request.getMemberId(), request.getCommentId());

        if (existingReport.isPresent()) {
            throw new ReportException(Error.DUPLICATE_REPORT);
        } else {
            Report reportEntity = reportMapper.toReportEntity(request);
            Report savedReport = reportQueryService.save(reportEntity);
            return savedReport.getId();
        }
    }

}
