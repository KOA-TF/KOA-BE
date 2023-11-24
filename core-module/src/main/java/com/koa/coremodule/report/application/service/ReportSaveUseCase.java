package com.koa.coremodule.report.application.service;

import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.report.application.dto.ReportRequest;
import com.koa.coremodule.report.application.mapper.ReportMapper;
import com.koa.coremodule.report.domain.entity.Report;
import com.koa.coremodule.report.domain.exception.ReportException;
import com.koa.coremodule.report.domain.service.ReportQueryService;
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

            Member member = reportQueryService.findMember(request.getMemberId());
            Comment comment = reportQueryService.findComment(request.getCommentId());
            reportEntity.setMember(member);
            reportEntity.setComment(comment);

            Report savedReport = reportQueryService.save(reportEntity);
            return savedReport.getId();
        }
    }

}
