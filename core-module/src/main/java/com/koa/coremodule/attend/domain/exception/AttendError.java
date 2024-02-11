package com.koa.coremodule.attend.domain.exception;

import com.koa.commonmodule.exception.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AttendError implements Error {
    DUPLICATE_ATTEND("이미 출석하였습니다.", 3000, HttpStatus.BAD_REQUEST);

    private final String message;
    private final int errorCode;
    private final HttpStatus httpStatusCode;
}