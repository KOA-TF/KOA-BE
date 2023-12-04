package com.koa.coremodule.comment.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {
    private String content;
    private Boolean isAnonymous;
}
