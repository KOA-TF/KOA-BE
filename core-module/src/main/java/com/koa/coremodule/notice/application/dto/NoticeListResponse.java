package com.koa.coremodule.notice.application.dto;

import java.time.LocalDate;

public record NoticeListResponse(Long noticeId,
                                 String curriculum,
                                 String team,
                                 String title,
                                 String content,
                                 String imageUrl,
                                 LocalDate date,
                                 Boolean viewYn) {

    public NoticeListResponse {
        if (viewYn == null) viewYn = false;
    }

    public NoticeListResponse withViewYn(Boolean newViewYn) {
        return new NoticeListResponse(noticeId, curriculum, team, title, content, imageUrl, date, newViewYn);
    }

}
