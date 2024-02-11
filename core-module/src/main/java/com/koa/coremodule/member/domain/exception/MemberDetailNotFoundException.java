package com.koa.coremodule.member.domain.exception;

import com.koa.commonmodule.exception.Error;

public class MemberDetailNotFoundException extends MemberException {
    public MemberDetailNotFoundException(Error error) { super(error); }
}
