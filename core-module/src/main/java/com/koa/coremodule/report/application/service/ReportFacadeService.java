package com.koa.coremodule.report.application.service;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.notice.domain.entity.NoticeReport;
import com.koa.coremodule.report.application.dto.ReportRequest;
import com.koa.coremodule.report.application.exception.ReportException;
import com.koa.coremodule.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportFacadeService {

    private final ReportRepository reportRepository;

    public Long create(ReportRequest request) {
        Optional<NoticeReport> existingReport = reportRepository.findById(request.memberId());

        if (existingReport.isPresent()) {
            throw new ReportException(Error.AUTH_FAIL);
        } else {
            NoticeReport report = new NoticeReport(); // NoticeReport 객체를 생성하거나 request에서 가져와서 생성합니다.
            NoticeReport savedReport = reportRepository.save(report);
            return savedReport.getId();
        }
    }
}
