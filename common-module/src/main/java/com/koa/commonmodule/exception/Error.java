package com.koa.commonmodule.exception;

import lombok.Getter;

@Getter
public enum Error {
    // Security
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 1001),
    EXPIRED_TOKEN("만료된 토큰입니다.", 1002),
    NOT_EXIST_TOKEN("토큰이 존재하지 않습니다.", 1003),
    INVALID_AUTHORIZATION_TYPE("유효하지 않은 Authorization Type 입니다.", 1004),
    EMPTY_AUTHORIZATION_HEADER("Authorization Header가 비어있습니다.", 1005),
    AUTH_FAIL("로그인에 실패했습니다.", 1006),

    //REPORT
    DUPLICATE_REPORT("이미 등록된 신고내용입니다.", 400),

    //NOTICE
    FILE_UPLOAD_FAIL("파일 업로드에 실패했습니다.", 500),
    WRONG_FILE_FORMAT("잘못된 파일 확장자입니다.", 400),

    // MEMBER
    MEMBER_NOT_FOUND("사용자를 찾을 수 없습니다.", 2000);
    private final String message;
    private final int errorCode;

    Error(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
