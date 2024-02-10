package com.koa.coremodule.report.domain.exception;
import com.koa.commonmodule.exception.Error;

public class ReportNotFoundException extends ReportException{
    public ReportNotFoundException(Error error) {
        super(error);
    }
}
