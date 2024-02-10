package com.koa.coremodule.comment.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.koa.commonmodule.exception.Error;

@Getter
@RequiredArgsConstructor
public enum CommentError implements Error {
    COMMENT_NOT_FOUND("댓글을 찾을 수 없습니다.", 3000, HttpStatus.NOT_FOUND),
    NOT_SAME_USER("작성자가 아닙니다.", 3001, HttpStatus.BAD_REQUEST);

    private final String message;
    private final int errorCode;
    private final HttpStatus httpStatusCode;
}
