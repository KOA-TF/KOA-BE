package com.koa.coremodule.vote.domain.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class VoteException extends BusinessException {
    public VoteException(Error error) {
        super(error);
    }
}
