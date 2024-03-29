package com.koa.coremodule.comment.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.comment.application.dto.request.CommentCreateRequest;
import com.koa.coremodule.comment.application.dto.response.CommentInfoResponse;
import com.koa.coremodule.comment.application.mapper.CommentMapper;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.service.CommentQueryService;
import com.koa.coremodule.comment.domain.service.CommentSaveService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.entity.MemberDetail;
import com.koa.coremodule.member.domain.service.MemberDetailQueryService;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.notice.domain.service.NoticeQueryService;
import com.koa.coremodule.notice.domain.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
public class CommentCreateUseCase {
    private final MemberUtils memberUtils;
    private final NoticeQueryService noticeQueryService;
    private final CommentSaveService commentSaveService;
    private final CommentQueryService commentQueryService;
    private final MemberDetailQueryService memberDetailQueryService;

    @Transactional
    public CommentInfoResponse createComment(Long noticeId, CommentCreateRequest commentCreateRequest){
        final Member member = memberUtils.getAccessMember();
        final Notice notice = noticeQueryService.findByNoticeId(noticeId);
        final Comment comment = CommentMapper.mapToCommentWithUserAndAlbum(commentCreateRequest.getContent(), notice, member);
        final MemberDetail memberDetail = memberDetailQueryService.findMemberDetailByMemberId(member.getId());

        commentSaveService.saveComment(comment);
        return CommentMapper.mapToCommentInfoResponse(comment, member, memberDetail);
    }

    @Transactional
    public CommentInfoResponse createReComment(Long noticeId, Long commentId, CommentCreateRequest commentCreateRequest){
        final Member member = memberUtils.getAccessMember();
        final Notice notice = noticeQueryService.findByNoticeId(noticeId);
        final Comment parent = commentQueryService.getCommentById(commentId);
        final Comment comment = CommentMapper.mapToCommentWithUserAndNoticeAndParent(commentCreateRequest.getContent(), notice, member, parent);
        final MemberDetail memberDetail = memberDetailQueryService.findMemberDetailByMemberId(member.getId());

        commentSaveService.saveComment(comment);
        return CommentMapper.mapToCommentInfoResponse(comment, member, memberDetail);
    }
}
