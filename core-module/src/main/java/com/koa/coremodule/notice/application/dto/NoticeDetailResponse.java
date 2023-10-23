package com.koa.coremodule.notice.application.dto;

public record NoticeDetailResponse(
        Long noticeId,
        String curriculumName,
        String teamName,
        String title,
        String content,
        String imageUrl
) {
}
