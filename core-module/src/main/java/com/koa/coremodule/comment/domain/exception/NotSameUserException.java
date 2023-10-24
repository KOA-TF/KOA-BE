package com.koa.coremodule.comment.domain.exception;

import com.koa.commonmodule.exception.Error;

public class NotSameUserException extends CommentException {
    public NotSameUserException(Error error) {
        super(error);
    }
}


