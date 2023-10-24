package com.koa.coremodule.notice.application.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class NoticeException extends BusinessException {

    public NoticeException(Error error) {
        super(error);
    }
}
