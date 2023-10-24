package com.koa.coremodule.notice.domain.exception;
import com.koa.commonmodule.exception.Error;

public class NoticeNotFoundException extends NoticeException {
    public NoticeNotFoundException(Error error) {
        super(error);
    }
}

