package com.koa.coremodule.notice.application.dto;

public record NoticeSelectRequest(Long memberId, Long cursor, Integer size) {
}
