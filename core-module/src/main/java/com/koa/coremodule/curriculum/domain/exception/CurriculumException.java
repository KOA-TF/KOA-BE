package com.koa.coremodule.curriculum.domain.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class CurriculumException extends BusinessException {
    public CurriculumException(Error error) {
        super(error);
    }
}

