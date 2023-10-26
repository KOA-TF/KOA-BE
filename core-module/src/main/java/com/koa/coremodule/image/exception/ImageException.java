package com.koa.coremodule.image.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class ImageException extends BusinessException {
    public ImageException(Error error) {
        super(error);
    }
}
