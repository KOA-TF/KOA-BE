package com.koa.coremodule.notice.application.dto;

import java.time.LocalDate;

public record NoticeDetailListResponse(Long noticeId,
                                       String name,
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
        return new NoticeDetailListResponse(noticeId, name, profileImage, curriculum, team, title, content, imageUrl, date, newViewYn);
    }

}
