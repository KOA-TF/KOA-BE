package com.koa.coremodule.image.exception;

import com.koa.commonmodule.exception.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ImageError implements Error {
    FILE_UPLOAD_FAIL("파일 업로드에 실패했습니다.", 6000, HttpStatus.INTERNAL_SERVER_ERROR),
    WRONG_FILE_FORMAT("잘못된 파일 확장자입니다.", 6001, HttpStatus.BAD_REQUEST),
    FILE_DELETE_FAIL("파일 삭제에 실패했습니다.", 6002, HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final int errorCode;
    private final HttpStatus httpStatusCode;

}
