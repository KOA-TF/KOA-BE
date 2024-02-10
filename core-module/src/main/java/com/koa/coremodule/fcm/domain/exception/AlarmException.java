package com.koa.coremodule.fcm.domain.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class AlarmException extends BusinessException {
    public AlarmException(Error error) {
        super(error);
    }
}

