package com.koa.coremodule.notice.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.koa.commonmodule.exception.Error;

@Getter
@RequiredArgsConstructor
public enum NoticeError implements Error {
    NOTICE_NOT_FOUND("공지사항을 찾을 수 없습니다.", 8000, HttpStatus.NOT_FOUND),
    NOTICE_TEAM_NOT_FOUND("공지 팀을 찾을 수 없습니다.", 8001, HttpStatus.NOT_FOUND);

    private final String message;
    private final int errorCode;
    private final HttpStatus httpStatusCode;
}
