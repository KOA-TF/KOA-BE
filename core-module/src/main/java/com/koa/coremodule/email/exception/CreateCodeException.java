package com.koa.coremodule.email.exception;
import com.koa.commonmodule.exception.Error;

public class CreateCodeException extends EmailException{
    public CreateCodeException(Error error) {
        super(error);
    }
}
