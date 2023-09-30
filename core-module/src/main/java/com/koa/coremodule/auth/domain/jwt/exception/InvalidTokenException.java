package com.koa.coremodule.auth.domain.jwt.exception;
import com.koa.commonmodule.exception.Error;

public class InvalidTokenException extends TokenException{
    public InvalidTokenException(Error error) {
        super(error);
    }
}
