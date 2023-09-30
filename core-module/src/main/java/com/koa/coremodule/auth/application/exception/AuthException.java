package com.koa.coremodule.auth.application.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class AuthException extends BusinessException {
    public AuthException(Error error) {
        super(error);
    }
}
