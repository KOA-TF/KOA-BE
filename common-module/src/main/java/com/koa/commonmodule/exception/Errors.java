package com.koa.commonmodule.exception;

import lombok.Getter;

@Getter
public enum Errors {
    // Security
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 1001),
    EXPIRED_TOKEN("만료된 토큰입니다.", 1002),
    NOT_EXIST_TOKEN("토큰이 존재하지 않습니다.", 1003),
    INVALID_AUTHORIZATION_TYPE("유효하지 않은 Authorization Type 입니다.", 1004),
    EMPTY_AUTHORIZATION_HEADER("Authorization Header가 비어있습니다.", 1005),
    AUTH_FAIL("로그인에 실패했습니다.", 1006),

    //REPORT
    DUPLICATE_REPORT("이미 등록된 신고내용입니다.", 2000),
    REPORT_NOT_FOUND("신고가 존재하지 않습니다.", 2001),

    //ATTEND
    DUPLICATE_ATTEND("이미 출석하였습니다.", 3000),

    // VOTE
    VOTE_NOT_FOUND("투표가 존재하지 않습니다.", 400),
    VOTE_ITEM_NOT_FOUND("투표 항목이 존재하지 않습니다.", 400),
    VOTE_ITEM_RECORD_NOT_FOUND("투표 항목 참여자가 존재하지 않습니다.", 400),

    // ALARM
    ALARM_NOT_FOUND("알림이 존재하지 않습니다.", 400),
    DUPLICATE_ALARM("이미 조회 완료된 알림입니다.", 400),

    // MEMBER
    MEMBER_NOT_FOUND("사용자를 찾을 수 없습니다.", 2000),
    WRONG_PASSWORD("비밀번호가 틀렸습니다.", 2001),
    MEMBER_DEAITL_NOT_FOUND("사용자 상세 정보를 찾을 수 없습니다.", 2002),

    //NOTICE
    NOTICE_NOT_FOUND("공지사항을 찾을 수 없습니다.", 3000),
    NOTICE_TEAM_NOT_FOUND("공지 팀을 찾을 수 없습니다.", 400),

    //CURRICULUM
    CURRICULUM_NOT_FOUND("커리큘럼을 찾을 수 없습니다.", 400),

    //COMMENT
    COMMENT_NOT_FOUND("댓글을 찾을 수 없습니다.", 4000),
    NOT_SAME_USER("작성자가 아닙니다.", 4001),

    // IMAGE
    FILE_UPLOAD_FAIL("파일 업로드에 실패했습니다.", 5000),
    WRONG_FILE_FORMAT("잘못된 파일 확장자입니다.", 5001),
    FILE_DELETE_FAIL("파일 삭제에 실패했습니다.", 5002),

    //Email
    CREATE_CODE_FAIL("인증코드 생성에 실패했습니다.", 6000),
    WRONG_CODE("잘못된 인증코드입니다.", 6001),

    //Team
    TEAM_NOT_FOUND("팀을 찾을 수 없습니다.", 7000),
    ALREADY_ENROLL("이미 참여한 팀입니다.", 7001);

    private final String message;
    private final int errorCode;

    Errors(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
