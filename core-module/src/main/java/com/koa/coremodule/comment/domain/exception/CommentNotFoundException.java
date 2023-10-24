package com.koa.coremodule.comment.domain.exception;
import com.koa.commonmodule.exception.Error;

public class CommentNotFoundException extends CommentException {
    public CommentNotFoundException(Error error) {
        super(error);
    }
}

