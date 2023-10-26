package com.koa.coremodule.image.exception;
import com.koa.commonmodule.exception.Error;

public class FileDeleteException extends ImageException{
    public FileDeleteException(Error error) {
        super(error);
    }
}
