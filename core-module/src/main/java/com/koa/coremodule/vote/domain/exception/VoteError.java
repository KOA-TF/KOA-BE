package com.koa.coremodule.vote.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.koa.commonmodule.exception.Error;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum VoteError implements Error {
    VOTE_NOT_FOUND("투표가 존재하지 않습니다.", 11000, HttpStatus.NOT_FOUND),
    VOTE_ITEM_NOT_FOUND("투표 항목이 존재하지 않습니다.", 11001, HttpStatus.NOT_FOUND),
    VOTE_ITEM_RECORD_NOT_FOUND("투표 항목 참여자가 존재하지 않습니다.", 11002, HttpStatus.NOT_FOUND);

    private final String message;
    private final int errorCode;
    private final HttpStatus httpStatusCode;
}
