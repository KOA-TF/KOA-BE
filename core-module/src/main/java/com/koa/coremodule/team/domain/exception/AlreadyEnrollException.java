package com.koa.coremodule.team.domain.exception;

import com.koa.commonmodule.exception.Error;

public class AlreadyEnrollException extends TeamException {
    public AlreadyEnrollException(Error error) {
        super(error);
    }
}
