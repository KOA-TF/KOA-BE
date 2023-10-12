package com.koa.coremodule.report.application.service;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.notice.domain.entity.NoticeReport;
import com.koa.coremodule.report.application.dto.ReportRequest;
import com.koa.coremodule.report.application.exception.ReportException;
import com.koa.coremodule.report.mapper.ReportMapper;
import com.koa.coremodule.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportFacadeService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    public Long create(ReportRequest request) {

        Optional<NoticeReport> existingReport = reportRepository.findById(request.getCommentId());

        if (existingReport.isPresent()) {
            throw new ReportException(Error.DUPLICATE_REPORT);
        } else {
            NoticeReport reportEntity = reportMapper.toReportEntity(request);
            NoticeReport savedReport = reportRepository.save(reportEntity);
            return savedReport.getId();
        }
    }
}
