package com.koa.coremodule.member.application.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class WrongPasswordException extends BusinessException {
    public WrongPasswordException(Error error) {
        super(error);
    }
}
