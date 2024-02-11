package com.koa.coremodule.report.domain.exception;
import com.koa.commonmodule.exception.Error;

public class DuplicateReportException extends ReportException{
    public DuplicateReportException(Error error) {
        super(error);
    }
}
