package com.koa.coremodule.comment.domain.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class CommentException extends BusinessException {
    public CommentException(Error error) {
        super(error);
    }
}
