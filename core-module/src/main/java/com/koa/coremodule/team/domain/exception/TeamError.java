package com.koa.coremodule.team.domain.exception;

import com.koa.commonmodule.exception.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TeamError implements Error {
    TEAM_NOT_FOUND("팀을 찾을 수 없습니다.", 10000, HttpStatus.NOT_FOUND),
    ALREADY_ENROLL("이미 참여한 팀입니다.", 10001, HttpStatus.BAD_REQUEST);

    private final String message;
    private final int errorCode;
    private final HttpStatus httpStatusCode;
}
