package com.koa.coremodule.member.domain.exception;
import com.koa.commonmodule.exception.Error;

public class MemberNotFoundException extends MemberException {
    public MemberNotFoundException(Error error) { super(error); }
}
