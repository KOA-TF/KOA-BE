package com.koa.coremodule.attend.domain.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class AttendException extends BusinessException {
    public AttendException(Error error) {
        super(error);
    }
}

