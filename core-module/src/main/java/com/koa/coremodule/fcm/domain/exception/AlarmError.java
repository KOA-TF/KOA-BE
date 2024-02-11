package com.koa.coremodule.fcm.domain.exception;

import com.koa.commonmodule.exception.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AlarmError implements Error {
    ALARM_NOT_FOUND("알림이 존재하지 않습니다.", 5000, HttpStatus.NOT_FOUND),
    DUPLICATE_ALARM("이미 조회 완료된 알림입니다.", 5001, HttpStatus.BAD_REQUEST);

    private final String message;
    private final int errorCode;
    private final HttpStatus httpStatusCode;
}
