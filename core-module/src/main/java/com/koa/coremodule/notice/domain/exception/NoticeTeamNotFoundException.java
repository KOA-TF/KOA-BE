package com.koa.coremodule.notice.domain.exception;
import com.koa.commonmodule.exception.Error;

public class NoticeTeamNotFoundException extends NoticeException {
    public NoticeTeamNotFoundException(Error error) {
        super(error);
    }
}
