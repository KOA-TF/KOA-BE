package com.koa.coremodule.comment.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.comment.application.dto.response.CommentInfoResponse;
import com.koa.coremodule.comment.application.dto.response.CommentListResponse;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.service.CommentQueryService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.entity.MemberDetail;
import com.koa.coremodule.member.domain.service.MemberDetailQueryService;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentGetUseCase {
    private final CommentQueryService commentQueryService;
    private final MemberUtils memberUtils;
    private final MemberDetailQueryService memberDetailQueryService;

    public List<CommentListResponse> getCommentList(Long noticeId){
        final Member member = memberUtils.getAccessMember();
        final Map<Long, List<Comment>> childCommentMap = commentQueryService.getChildCommentByNoticeId(noticeId);
        final ArrayList<CommentListResponse> commentListResponses = new ArrayList<>();
        final Map<Comment, MemberDetail> commentMemberDetailMap = createCommentMemberDetailMapWithNoticeId(noticeId);
        for (Comment comment : commentMemberDetailMap.keySet()) {
            final List<Comment> childCommentList = childCommentMap.get(comment.getId());
            commentListResponses.add(new CommentListResponse(comment.getId(), comment.getContent(), comment.getWriter().getName(),
                    comment.getCreatedAtByFormat() ,childCommentList != null ? childCommentList.size() : 0,
                    comment.getWriter().equals(member), comment.getIsAnonymous(), commentMemberDetailMap.get(comment).getProfileImage(),
                    comment.getWriter().getId()));
        }
        return commentListResponses;
    }

    public List<CommentInfoResponse> getReCommentList(Long commentId){
        final Member member = memberUtils.getAccessMember();
        final Map<Comment, MemberDetail> childCommentMemberDetailMap = createCommentMemberDetailMapWithCommentId(commentId);
        final ArrayList<CommentInfoResponse> childCommentInfoResponses = new ArrayList<>();
        for (Comment comment : childCommentMemberDetailMap.keySet()) {
            childCommentInfoResponses.add(new CommentInfoResponse(comment.getId(), comment.getContent()
                    , comment.getWriter().getName(), comment.getCreatedAtByFormat(),
                    comment.getWriter().equals(member), comment.getIsAnonymous(),
                    childCommentMemberDetailMap.get(comment).getProfileImage(), comment.getWriter().getId()));
        }
        return childCommentInfoResponses;
    }

    private <T> Map<Comment, MemberDetail> createCommentMemberDetailMap(Long id, Function<Long, List<Comment>> commentGetter) {
        final List<Comment> commentList = commentGetter.apply(id);
        final List<Long> memberIdList = commentList.stream()
                .map(comment -> comment.getWriter().getId())
                .collect(Collectors.toList());
        final List<MemberDetail> memberDetailList = memberDetailQueryService.findMemberDetailListByMemberIdList(memberIdList);
        final Map<Long, MemberDetail> memberDetailMap = memberDetailList.stream()
                .collect(Collectors.toMap(memberDetail -> memberDetail.getMember().getId(), memberDetail -> memberDetail));
        final Map<Comment, MemberDetail> commentMemberDetailMap = commentList.stream()
                .sorted(Comparator.comparing(Comment::getCreatedAt))
                .collect(Collectors.toMap(
                        comment -> comment,
                        comment -> memberDetailMap.get(comment.getWriter().getId()),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
        return commentMemberDetailMap;
    }

    public Map<Comment, MemberDetail> createCommentMemberDetailMapWithNoticeId(Long noticeId) {
        return createCommentMemberDetailMap(noticeId, commentQueryService::getCommentByNoticeId);
    }

    public Map<Comment, MemberDetail> createCommentMemberDetailMapWithCommentId(Long commentId) {
        return createCommentMemberDetailMap(commentId, commentQueryService::getChildCommentByCommentId);
    }
}
