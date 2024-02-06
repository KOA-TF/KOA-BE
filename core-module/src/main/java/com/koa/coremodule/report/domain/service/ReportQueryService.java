package com.koa.coremodule.report.domain.service;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.repository.CommentRepository;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.repository.MemberRepository;
import com.koa.coremodule.report.domain.entity.Report;
import com.koa.coremodule.report.domain.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportQueryService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    public List<Report> findAll() {

        return reportRepository.findAll();
    }

    public Optional<Report> findByIds(Long memberId, Long commentId) {

        return Optional.ofNullable(reportRepository.findByCommentIdAndMemberId(memberId, commentId));
    }

    public Report save(Report report) {

        return reportRepository.save(report);
    }

    public void hideReport(Long reportId) {

        Report report = reportRepository.findById(reportId).orElseThrow(() -> new BusinessException(Error.REPORT_NOT_FOUND));
        report.hide();
    }

    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId).orElseThrow(() -> new BusinessException(Error.MEMBER_NOT_FOUND));
    }

    public Comment findComment(Long commentId) {

        return commentRepository.findById(commentId).orElseThrow(() -> new BusinessException(Error.COMMENT_NOT_FOUND));
    }
}
