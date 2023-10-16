package com.koa.coremodule.report.application.exception;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;

public class ReportException extends BusinessException {

    public ReportException(Error error) {
        super(error);
    }

}
