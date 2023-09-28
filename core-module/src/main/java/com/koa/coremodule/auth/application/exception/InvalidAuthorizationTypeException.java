package com.koa.coremodule.auth.application.exception;
import com.koa.commonmodule.exception.Error;
public class InvalidAuthorizationTypeException extends AuthException {
    public InvalidAuthorizationTypeException(Error error) {
        super(error);
    }
}
