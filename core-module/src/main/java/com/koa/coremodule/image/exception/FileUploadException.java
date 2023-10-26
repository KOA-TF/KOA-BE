package com.koa.coremodule.image.exception;

import com.koa.commonmodule.exception.Error;

public class FileUploadException extends ImageException {
    public FileUploadException(Error error) {
        super(error);
    }
}

