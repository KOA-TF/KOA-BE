package com.koa.coremodule.notice.application.dto;

import java.time.LocalDate;

public record NoticeDetailListResponse(Long noticeId,
                                       String writer,
                                       String profileImage,
                                       String curriculum,
                                       String team,
                                       String title,
                                       String content,
                                       String imageUrl,
                                       LocalDate date,
                                       Boolean viewYn) {

    public NoticeDetailListResponse {
        if (viewYn == null) viewYn = false;
    }

    public NoticeDetailListResponse withViewYn(Boolean newViewYn) {
        return new NoticeDetailListResponse(noticeId, writer, profileImage, curriculum, team, title, content, imageUrl, date, newViewYn);
    }

}
