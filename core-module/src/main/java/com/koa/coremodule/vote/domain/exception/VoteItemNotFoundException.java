package com.koa.coremodule.vote.domain.exception;
import com.koa.commonmodule.exception.Error;

public class VoteItemNotFoundException extends VoteException{
    public VoteItemNotFoundException(Error error) {
        super(error);
    }
}
