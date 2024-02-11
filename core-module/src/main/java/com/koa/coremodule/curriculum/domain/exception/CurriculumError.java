package com.koa.coremodule.curriculum.domain.exception;

import com.koa.commonmodule.exception.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CurriculumError implements Error {
    CURRICULUM_NOT_FOUND("커리큘럼을 찾을 수 없습니다.", 4000, HttpStatus.NOT_FOUND);

    private final String message;
    private final int errorCode;
    private final HttpStatus httpStatusCode;
}
