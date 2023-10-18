package com.koa.coremodule.comment.application.mapper;

import com.koa.coremodule.comment.application.dto.response.CommentInfoResponse;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.notice.domain.entity.Notice;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {

    public static Comment mapToCommentWithUserAndAlbum(String content, Notice notice, Member member) {
        Comment comment = Comment.builder()
                .content(content)
                .notice(notice)
                .writer(member)
                .build();
        return comment;
    }

    public static Comment mapToCommentWithUserAndNoticeAndParent(String content, Notice notice, Member member, Comment parent) {
        Comment comment = Comment.builder()
                .content(content)
                .notice(notice)
                .writer(member)
                .parent(parent)
                .build();
        return comment;
    }

    public static CommentInfoResponse mapToCommentInfoResponse(Comment comment, Member member) {
        return CommentInfoResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .writer(member.getName())
                .build();
    }

}
