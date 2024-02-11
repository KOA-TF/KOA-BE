package com.koa.coremodule.vote.domain.exception;
import com.koa.commonmodule.exception.Error;

public class VoteNotFoundException extends VoteException {
    public VoteNotFoundException(Error error) {
        super(error);
    }
}
