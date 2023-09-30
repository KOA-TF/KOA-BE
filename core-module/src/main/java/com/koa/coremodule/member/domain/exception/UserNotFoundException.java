package com.koa.coremodule.member.domain.exception;
import com.koa.commonmodule.exception.Error;

public class UserNotFoundException extends UserException{
    public UserNotFoundException(Error error) { super(error); }
}
