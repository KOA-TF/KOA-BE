package com.koa.coremodule.notice.application.dto;

import java.time.LocalDate;

public record NoticeDetailInfoResponse(Long noticeId,
                                       String name,
                                       String profileImage,
                                       String curriculum,
                                       String team,
                                       String title,
                                       String content,
                                       LocalDate date,
                                       Boolean viewYn) {

    public NoticeDetailInfoResponse {
        if (viewYn == null) viewYn = false;
    }

    public NoticeDetailInfoResponse withViewYn(Boolean newViewYn) {
        return new NoticeDetailInfoResponse(noticeId, name, profileImage, curriculum, team, title, content, date, newViewYn);
    }

}
