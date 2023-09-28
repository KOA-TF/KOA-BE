package com.koa.coremodule.member.domain.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class UserException extends BusinessException {
    public UserException(Error error) {
        super(error);
    }
}
