package com.koa.coremodule.admin.application.service;

import com.koa.coremodule.admin.application.dto.AdminMemberList;
import com.koa.coremodule.admin.application.dto.AdminReportList;
import com.koa.coremodule.admin.application.mapper.AdminMapper;
import com.koa.coremodule.admin.domain.service.AdminQueryService;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import com.koa.coremodule.report.domain.service.ReportQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminGetUseCase {

    private final AdminQueryService adminQueryService;
    private final ReportQueryService reportQueryService;
    private final MemberQueryService memberQueryService;

    public Long checkPassword(Integer password) {

        return adminQueryService.checkPassword(password);
    }

    public List<AdminMemberList> getMemberList() {

        return memberQueryService.findAllMember()
                .stream()
                .map(AdminMapper::mapToMemberList)
                .toList();
    }

    public List<AdminReportList> getReportList() {

        return reportQueryService.findAll()
                .stream()
                .map(AdminMapper::mapToReportList)
                .toList();
    }

    public void hideReport(Long reportId) {

        reportQueryService.hideReport(reportId);
    }
}
