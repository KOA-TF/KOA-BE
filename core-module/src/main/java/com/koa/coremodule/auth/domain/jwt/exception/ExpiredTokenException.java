package com.koa.coremodule.auth.domain.jwt.exception;
import com.koa.commonmodule.exception.Error;

public class ExpiredTokenException extends TokenException {
    public ExpiredTokenException(Error error) {
        super(error);
    }
}
