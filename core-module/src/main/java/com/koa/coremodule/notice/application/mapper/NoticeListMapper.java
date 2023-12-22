package com.koa.coremodule.notice.application.mapper;

import com.koa.coremodule.notice.application.dto.NoticeListResponse;
import com.koa.coremodule.notice.application.dto.NoticeV2ListResponse;

import java.util.ArrayList;

public final class NoticeListMapper {

    private NoticeListMapper() {
    }

    public static NoticeV2ListResponse toListResponse(NoticeListResponse response) {
        return NoticeV2ListResponse.builder()
                .noticeId(response.noticeId())
                .curriculumName(response.curriculumName())
                .teamName(response.teamName())
                .title(response.title())
                .content(response.content())
                .date(response.date())
                .viewYn(response.viewYn())
                .imageUrl(new ArrayList<>())
                .build();
    }

}
