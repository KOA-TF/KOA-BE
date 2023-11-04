package com.koa.coremodule.email.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class EmailException extends BusinessException {
    public EmailException(Error error) {
        super(error);
    }
}
