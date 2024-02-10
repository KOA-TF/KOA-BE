package com.koa.coremodule.fcm.domain.exception;
import com.koa.commonmodule.exception.Error;

public class DuplicateAlarmException extends AlarmException{
    public DuplicateAlarmException(Error error) {
        super(error);
    }
}
