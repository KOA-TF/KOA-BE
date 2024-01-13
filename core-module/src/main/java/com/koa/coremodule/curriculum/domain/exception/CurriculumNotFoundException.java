package com.koa.coremodule.curriculum.domain.exception;
import com.koa.commonmodule.exception.Error;

public class CurriculumNotFoundException extends CurriculumException{
    public CurriculumNotFoundException(Error error) {
        super(error);
    }
}
