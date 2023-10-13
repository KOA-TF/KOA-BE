package com.koa.apimodule.command.api.report.service;

import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.notice.domain.entity.NoticeReport;
import com.koa.coremodule.report.application.dto.ReportRequest;
import com.koa.coremodule.report.application.exception.ReportException;
import com.koa.coremodule.report.application.service.ReportFacadeService;
import com.koa.coremodule.report.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportUseCase {

    private final ReportFacadeService reportFacadeService;
    private final ReportMapper reportMapper;

    public Long createReport (ReportRequest request) {

        Optional<NoticeReport> existingReport = reportFacadeService.findById(request.getCommentId());

        if (existingReport.isPresent()) {
            throw new ReportException(Error.DUPLICATE_REPORT);
        } else {
            NoticeReport reportEntity = reportMapper.toReportEntity(request);
            NoticeReport savedReport = reportFacadeService.save(reportEntity);
            return savedReport.getId();
        }
    }

}
