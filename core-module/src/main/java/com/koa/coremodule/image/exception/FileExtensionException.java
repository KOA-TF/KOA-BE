package com.koa.coremodule.image.exception;
import com.koa.commonmodule.exception.Error;


public class FileExtensionException extends ImageException{
    public FileExtensionException(Error error) {
        super(error);
    }
}
