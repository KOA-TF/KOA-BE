package com.koa.coremodule.comment.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.comment.application.dto.response.CommentInfoResponse;
import com.koa.coremodule.comment.application.dto.response.CommentListResponse;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.service.CommentQueryService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentReadUseCase {
    private final CommentQueryService commentQueryService;
    private final MemberUtils memberUtils;

    public List<CommentListResponse> getCommentList(Long noticeId){
        final Member member = memberUtils.getAccessMember();
        final Map<Long, List<Comment>> childCommentMap = commentQueryService.getChildCommentByNoticeId(noticeId);
        final ArrayList<CommentListResponse> commentListResponses = new ArrayList<>();
        List<Comment> commentList = commentQueryService.getCommentByNoticeId(noticeId);
        for (Comment comment : commentList) {
            final List<Comment> childCommentList = childCommentMap.get(comment.getId());
            commentListResponses.add(new CommentListResponse(comment.getId(), comment.getContent(), comment.getWriter().getName(), comment.getCreatedAt().toString()
                    ,childCommentList != null ? childCommentList.size() : 0, comment.getWriter().equals(member)));
        }
        return commentListResponses;
    }

    public List<CommentInfoResponse> getReCommentList(Long commentId){
        final Member member = memberUtils.getAccessMember();
        final List<Comment> childCommentList = commentQueryService.getChildCommentByCommentId(commentId);
        final ArrayList<CommentInfoResponse> childCommentInfoResponses = new ArrayList<>();
        for (Comment comment : childCommentList) {
            childCommentInfoResponses.add(new CommentInfoResponse(comment.getId(), comment.getContent()
                    , comment.getWriter().getName(), comment.getCreatedAt().toString(), comment.getWriter().equals(member)));
        }
        return childCommentInfoResponses;
    }
}
