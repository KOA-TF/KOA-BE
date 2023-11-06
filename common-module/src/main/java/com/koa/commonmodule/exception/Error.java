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

    // MEMBER
    MEMBER_NOT_FOUND("사용자를 찾을 수 없습니다.", 2000),
    WRONG_PASSWORD("비밀번호가 틀렸습니다.", 2001),

    //NOTICE
    NOTICE_NOT_FOUND("공지사항을 찾을 수 없습니다.", 3000),

    //COMMENT
    COMMENT_NOT_FOUND("댓글을 찾을 수 없습니다.", 4000),
    NOT_SAME_USER("작성자가 아닙니다.", 4001),

    // IMAGE
    FILE_UPLOAD_FAIL("파일 업로드에 실패했습니다.", 5000),
    WRONG_FILE_FORMAT("잘못된 파일 확장자입니다.", 5001),
    FILE_DELETE_FAIL("파일 삭제에 실패했습니다.", 5002),

    //Email
    CREATE_CODE_FAIL("인증코드 생성에 실패했습니다.", 6000),
    WRONG_CODE("잘못된 인증코드입니다.", 6001);

    private final String message;
    private final int errorCode;

    Error(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
