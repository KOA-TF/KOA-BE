package com.koa.coremodule.member.domain.exception;

import com.koa.commonmodule.exception.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberError implements Error {
    MEMBER_NOT_FOUND("사용자를 찾을 수 없습니다.", 7000, HttpStatus.NOT_FOUND),
    WRONG_PASSWORD("비밀번호가 틀렸습니다.", 7001, HttpStatus.BAD_REQUEST),
    MEMBER_DEAITL_NOT_FOUND("사용자 상세 정보를 찾을 수 없습니다.", 7002, HttpStatus.NOT_FOUND);


    private final String message;
    private final int errorCode;
    private final HttpStatus httpStatusCode;

}
