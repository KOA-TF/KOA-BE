package com.koa.coremodule.comment.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CommentListResponse {
    private final Long commentId;
    private final String content;
    private final String writer;
    private final String createdAt;
    private final Integer commentCount;
    private final Boolean isMine;
    private final String profileImageUrl;
    private final Long writerId;
}
