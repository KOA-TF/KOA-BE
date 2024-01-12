package com.koa.coremodule.team.domain.exception;
import com.koa.commonmodule.exception.Error;

public class TeamNotFoundException extends TeamException {
    public TeamNotFoundException(Error error) {
        super(error);
    }
}
