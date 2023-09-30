package com.koa.coremodule.auth.domain.jwt.exception;


import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class TokenException extends BusinessException {
    public TokenException(Error error) {
        super(error);
    }
}
