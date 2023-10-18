package com.koa.coremodule.notice.domain.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class NoticeException extends BusinessException {
    public NoticeException(Error error) {
        super(error);
    }
}

