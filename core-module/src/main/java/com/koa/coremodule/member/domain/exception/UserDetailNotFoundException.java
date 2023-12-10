package com.koa.coremodule.member.domain.exception;

import com.koa.commonmodule.exception.Error;

public class UserDetailNotFoundException extends UserException{
    public UserDetailNotFoundException(Error error) { super(error); }
}
