package com.koa.coremodule.email.exception;
import com.koa.commonmodule.exception.Error;

public class WrongCodeException extends EmailException{
    public WrongCodeException(Error error) {
        super(error);
    }
}
