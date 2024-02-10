package com.koa.coremodule.member.domain.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class MemberException extends BusinessException {
    public MemberException(Error error) {
        super(error);
    }
}
