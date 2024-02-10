package com.koa.coremodule.fcm.domain.exception;
import com.koa.commonmodule.exception.Error;

public class AlarmNotFoundException extends AlarmException{
    public AlarmNotFoundException(Error error) {
        super(error);
    }
}
