package com.koa.coremodule.auth.domain.exception;

import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.auth.domain.jwt.exception.TokenException;

public class NotExistTokenException extends TokenException {
    public NotExistTokenException(Error error) {
        super(error);
    }
}
