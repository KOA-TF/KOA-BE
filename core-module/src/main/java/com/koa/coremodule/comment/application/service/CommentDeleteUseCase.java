package com.koa.coremodule.comment.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.service.CommentDeleteService;
import com.koa.coremodule.comment.domain.service.CommentQueryService;
import com.koa.coremodule.comment.domain.service.CommentValidateService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class CommentDeleteUseCase {

    private final MemberUtils memberUtils;
    private final CommentQueryService commentQueryService;
    private final CommentDeleteService commentDeleteService;
    private final CommentValidateService commentValidateService;

    public void deleteComment(Long commentId) {
        final Member member = memberUtils.getAccessMember();
        final Comment comment = commentQueryService.getCommentById(commentId);
        commentValidateService.validateSameUser(comment, member.getId());
        commentDeleteService.deleteComment(comment);
    }

}
