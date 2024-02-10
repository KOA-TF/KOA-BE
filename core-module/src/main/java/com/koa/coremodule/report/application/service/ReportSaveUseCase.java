package com.koa.coremodule.report.application.service;

import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.service.CommentQueryService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import com.koa.coremodule.report.application.dto.ReportRequest;
import com.koa.coremodule.report.application.mapper.ReportMapper;
import com.koa.coremodule.report.domain.entity.Report;
import com.koa.coremodule.report.domain.service.ReportQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ReportSaveUseCase {

    private final ReportQueryService reportQueryService;
    private final MemberQueryService memberQueryService;
    private final CommentQueryService commentQueryService;
    private final ReportMapper reportMapper;

    public Long createReport(ReportRequest request) {

        reportQueryService.checkReportExists(request.getMemberId(), request.getCommentId());
        Report reportEntity = reportMapper.toReportEntity(request);

        Member member = memberQueryService.findMemberById(request.getMemberId());
        Comment comment = commentQueryService.getCommentById(request.getCommentId());
        reportEntity.setMember(member);
        reportEntity.setComment(comment);

        Report savedReport = reportQueryService.save(reportEntity);
        return savedReport.getId();

    }

}
