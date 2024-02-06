package com.koa.coremodule.comment.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.exception.NotSameUserException;
import com.koa.coremodule.comment.domain.service.CommentDeleteService;
import com.koa.coremodule.comment.domain.service.CommentQueryService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import com.koa.commonmodule.exception.Error;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class CommentDeleteUseCase {

    private final MemberUtils memberUtils;
    private final CommentQueryService commentQueryService;
    private final CommentDeleteService commentDeleteService;

    public void deleteComment(Long commentId) {
        final Member member = memberUtils.getAccessMember();
        final Comment comment = commentQueryService.getCommentById(commentId);
        validateAccess(comment, member.getId());
        commentDeleteService.deleteComment(comment);
    }

    public void validateAccess(Comment comment, Long userId){
        if(!comment.getWriter().getId().equals(userId)){
            throw new NotSameUserException(Error.NOT_SAME_USER);
        }
    }
}
