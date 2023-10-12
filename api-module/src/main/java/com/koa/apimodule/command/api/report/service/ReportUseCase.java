package com.koa.apimodule.command.api.report.service;

import com.koa.coremodule.report.application.dto.ReportRequest;
import com.koa.coremodule.report.application.service.ReportFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportUseCase {

    private final ReportFacadeService reportFacadeService;

    public Long create(ReportRequest request) {

        return reportFacadeService.create(request);
    }

}
