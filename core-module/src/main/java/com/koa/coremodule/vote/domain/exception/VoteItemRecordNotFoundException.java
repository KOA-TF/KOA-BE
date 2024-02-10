package com.koa.coremodule.vote.domain.exception;
import com.koa.commonmodule.exception.Error;

public class VoteItemRecordNotFoundException extends VoteException{
    public VoteItemRecordNotFoundException(Error error) {
        super(error);
    }
}
