package com.koa.coremodule.report.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.koa.commonmodule.exception.Error;

@Getter
@RequiredArgsConstructor
public enum ReportError implements Error {
    DUPLICATE_REPORT("이미 등록된 신고내용입니다.", 9000, HttpStatus.BAD_REQUEST),
    REPORT_NOT_FOUND("신고가 존재하지 않습니다.", 9001, HttpStatus.NOT_FOUND);

    private final String message;
    private final int errorCode;
    private final HttpStatus httpStatusCode;
}
