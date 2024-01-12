package com.koa.coremodule.team.domain.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class TeamException extends BusinessException {
    public TeamException(Error error) {
        super(error);
    }
}
